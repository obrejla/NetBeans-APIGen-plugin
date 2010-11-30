/*
 *  The MIT License
 * 
 *  Copyright 2010 Ondřej Brejla <ondrej@brejla.cz>.
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 * 
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 * 
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package org.netbeans.modules.php.apigen.actions.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.netbeans.api.extexecution.ExternalProcessBuilder;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.apigen.actions.GenerateApiAction;
import org.netbeans.modules.php.apigen.options.ApiGenPanel;
import org.netbeans.modules.php.project.ui.options.PhpOptions;
import org.openide.DialogDescriptor;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class ApiGenActionListener implements ActionListener {

	private String apiGenTab = NbBundle.getMessage(GenerateApiAction.class, "LBL_ApiGenTab");

	private ApiGenActionPanel panel;

	private PhpModule phpModule;

	public ApiGenActionListener(ApiGenActionPanel panel, PhpModule phpModule) {
		this.panel = panel;
		this.phpModule = phpModule;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == DialogDescriptor.OK_OPTION) {
			ExecutionDescriptor descriptor = new ExecutionDescriptor().frontWindow(true).controllable(true);

			String path = PhpOptions.getInstance().getPhpInterpreter();

			ExternalProcessBuilder processBuilder = new ExternalProcessBuilder(path)
					.workingDirectory(FileUtil.toFile(phpModule.getSourceDirectory()))
					.addArgument(NbPreferences.forModule(GenerateApiAction.class).get(ApiGenPanel.APIGEN_PATH_OPTION_KEY, ""))
					.addArgument("-s")
					.addArgument(panel.getSourceDirectory())
					.addArgument("-d")
					.addArgument(panel.getTargetDirectory());

			if (!panel.getOutputCfgFile().trim().isEmpty()) {
				processBuilder.addArgument("-c").addArgument(panel.getOutputCfgFile());
			}

			if (!panel.getDocumentationTitle().trim().isEmpty()) {
				processBuilder.addArgument("-t").addArgument(panel.getDocumentationTitle());
			}

			final ExecutionService service = ExecutionService.newService(processBuilder, descriptor, apiGenTab);

			new Thread(new Runnable() {

				@Override
				public void run() {
					Future<Integer> task = service.run();
					try {
						task.get();
						FileUtil.refreshFor(new File(panel.getTargetDirectory()));
					} catch (InterruptedException ex) {
						Exceptions.printStackTrace(ex);
					} catch (ExecutionException ex) {
						Exceptions.printStackTrace(ex);
					}
				}

			}).start();
		}
	}

}
