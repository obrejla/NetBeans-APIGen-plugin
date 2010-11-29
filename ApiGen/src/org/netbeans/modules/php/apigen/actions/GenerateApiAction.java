/*
 * The MIT license
 *
 * Copyright (c) 2010 Ondřej Brejla <ondrej@brejla.cz>
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package org.netbeans.modules.php.apigen.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.apigen.options.ApiGenPanel;
import org.netbeans.modules.php.project.ui.options.PhpOptions;
import org.openide.filesystems.FileUtil;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;


/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class GenerateApiAction extends AbstractAction {

	private static GenerateApiAction INSTANCE = new GenerateApiAction();

	private String apiGenActions = NbBundle.getMessage(GenerateApiAction.class, "LBL_ApiGenActions");

	private String generateApiItem = NbBundle.getMessage(GenerateApiAction.class, "LBL_GenerateApiItem");

	private String apiGenTab = NbBundle.getMessage(GenerateApiAction.class, "LBL_ApiGenTab");

	public static GenerateApiAction getInstance() {
		return INSTANCE;
	}

	protected GenerateApiAction() {
        putValue("noIconInMenu", true); // NOI18N
        putValue(NAME, apiGenActions + " " + generateApiItem);
        putValue("menuText", generateApiItem); // NOI18N
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		PhpModule phpModule = PhpModule.inferPhpModule();
        if (phpModule == null) {
            return;
        }

		ExecutionDescriptor descriptor = new ExecutionDescriptor().frontWindow(true).controllable(true);

		String path = PhpOptions.getInstance().getPhpInterpreter();

		ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(path).
				addArgument(NbPreferences.forModule(GenerateApiAction.class).get(ApiGenPanel.APIGEN_PATH_OPTION_KEY, "")).
				addArgument("-s").
				addArgument(FileUtil.toFile(phpModule.getSourceDirectory()).getAbsolutePath()).
				addArgument("-d").
				addArgument("/var/www/brejla.cz/apigen").
				workingDirectory(FileUtil.toFile(phpModule.getSourceDirectory()));

        ExecutionService service = ExecutionService.newService(processBuilder, descriptor, apiGenTab);
        service.run();
	}

}
