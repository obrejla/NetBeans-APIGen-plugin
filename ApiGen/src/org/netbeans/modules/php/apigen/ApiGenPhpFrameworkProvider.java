/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.php.apigen;

import java.io.File;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.phpmodule.PhpModuleProperties;
import org.netbeans.modules.php.spi.commands.FrameworkCommandSupport;
import org.netbeans.modules.php.spi.editor.EditorExtender;
import org.netbeans.modules.php.spi.phpmodule.PhpFrameworkProvider;
import org.netbeans.modules.php.spi.phpmodule.PhpModuleActionsExtender;
import org.netbeans.modules.php.spi.phpmodule.PhpModuleExtender;
import org.netbeans.modules.php.spi.phpmodule.PhpModuleIgnoredFilesExtender;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class ApiGenPhpFrameworkProvider extends PhpFrameworkProvider {

	private static ApiGenPhpFrameworkProvider INSTANCE = new ApiGenPhpFrameworkProvider();

	public static ApiGenPhpFrameworkProvider getInstance() {
        return INSTANCE;
    }

	private ApiGenPhpFrameworkProvider() {
        super("ApiGen", "ApiGen description");
    }

	@Override
	public boolean isInPhpModule(PhpModule pm) {
		return true;
	}

	@Override
	public File[] getConfigurationFiles(PhpModule pm) {
		return new File[0];
	}

	@Override
	public PhpModuleExtender createPhpModuleExtender(PhpModule pm) {
		return null;
	}

	@Override
	public PhpModuleProperties getPhpModuleProperties(PhpModule pm) {
		return new PhpModuleProperties();
	}

	@Override
	public PhpModuleActionsExtender getActionsExtender(PhpModule pm) {
		return new ApiGenPhpModuleActionsExtender();
	}

	@Override
	public PhpModuleIgnoredFilesExtender getIgnoredFilesExtender(PhpModule pm) {
		return null;
	}

	@Override
	public FrameworkCommandSupport getFrameworkCommandSupport(PhpModule pm) {
		return null;
	}

	@Override
	public EditorExtender getEditorExtender(PhpModule pm) {
		return null;
	}

}
