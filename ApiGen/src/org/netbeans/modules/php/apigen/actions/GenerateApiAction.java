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

import org.netbeans.modules.php.apigen.ui.actions.ApiGenActionPanel;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.apigen.ui.actions.ApiGenActionListener;
import org.openide.DialogDescriptor;
import org.openide.DialogDisplayer;
import org.openide.util.NbBundle;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class GenerateApiAction extends AbstractAction {

	private static GenerateApiAction INSTANCE = new GenerateApiAction();

	private String apiGenActions = NbBundle.getMessage(GenerateApiAction.class, "LBL_ApiGenActions");

	private String generateApiItem = NbBundle.getMessage(GenerateApiAction.class, "LBL_GenerateApiItem");

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

		ApiGenActionPanel panel = new ApiGenActionPanel(phpModule);
		
		DialogDescriptor dd = new DialogDescriptor(panel, NbBundle.getMessage(GenerateApiAction.class, "LBL_DialogTitle"), true, new ApiGenActionListener(panel, phpModule));
		panel.setDialogDescriptor(dd);
		
		DialogDisplayer.getDefault().notify(dd);
	}

}
