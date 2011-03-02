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

package org.netbeans.modules.php.apigen.ui;

import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.apigen.ApiGenPhpFrameworkProvider;
import org.openide.filesystems.FileUtil;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public final class ApiGenPreferences {

	private static final String APIGEN_TARGET = "apigen-target"; // NOI18N

    private static final String APIGEN_TITLE = "apigen-title"; // NOI18N

	private static final String APIGEN_CONFIG = "apigen-config"; // NOI18N

	private ApiGenPreferences() {
	}

	public static void setApiGenTarget(PhpModule phpModule, String target) {
        getPreferences(phpModule).put(APIGEN_TARGET, target);
    }

	public static String getApiGenTarget(PhpModule phpModule) {
        Preferences preferences = getPreferences(phpModule);
        String target = preferences.get(APIGEN_TARGET, null);
        if (target == null) {
            target = FileUtil.toFile(phpModule.getSourceDirectory()).getAbsolutePath();
            setApiGenTarget(phpModule, target);
        }
        return target;
    }

	public static void setApiGenTitle(PhpModule phpModule, String title) {
        if (title.equals(getDefaultApiGenTitle(phpModule))) {
            return;
        }
        getPreferences(phpModule).put(APIGEN_TITLE, title);
    }

	public static String getApiGenTitle(PhpModule phpModule) {
        return getPreferences(phpModule).get(APIGEN_TITLE, getDefaultApiGenTitle(phpModule));
    }

	public static void setApiGenConfig(PhpModule phpModule, String config) {
		getPreferences(phpModule).put(APIGEN_CONFIG, config);
	}

	public static String getApiGenConfig(PhpModule phpModule) {
        return getPreferences(phpModule).get(APIGEN_CONFIG, "");
    }

	private static Preferences getPreferences(PhpModule phpModule) {
		//TODO: return phpModule.getPreferences(ApiGenPreferences.class, false);
		return phpModule.getPreferences(ApiGenPhpFrameworkProvider.class, false);
	}

	private static String getDefaultApiGenTitle(PhpModule phpModule) {
        return phpModule.getDisplayName();
    }

}
