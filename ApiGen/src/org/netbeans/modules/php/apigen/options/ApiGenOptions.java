
package org.netbeans.modules.php.apigen.options;

import java.util.prefs.PreferenceChangeListener;
import org.openide.util.NbPreferences;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class ApiGenOptions {

	public static final String APIGEN_PATH_OPTION_KEY = "apigen-path"; // NOI18N

	private ApiGenOptions() {
	}

	public static void setPath(String path) {
		NbPreferences.forModule(ApiGenPanel.class).put(APIGEN_PATH_OPTION_KEY, path);
	}

	public static String getPath() {
		return NbPreferences.forModule(ApiGenPanel.class).get(APIGEN_PATH_OPTION_KEY, "");
	}

	public static void addPreferenceChangeListener(PreferenceChangeListener listener) {
		NbPreferences.forModule(ApiGenPanel.class).addPreferenceChangeListener(listener);
	}

}
