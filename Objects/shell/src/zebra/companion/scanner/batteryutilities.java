
package zebra.companion.scanner;

import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RemoteObject;

public class batteryutilities {
    public static RemoteObject myClass;
	public batteryutilities() {
	}
    public static PCBA staticBA = new PCBA(null, batteryutilities.class);

public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _nativeme = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
public static zebra.companion.scanner.main _main = null;
public static zebra.companion.scanner.starter _starter = null;
public static zebra.companion.scanner.scanner _scanner = null;
public static Object[] GetGlobals(RemoteObject _ref) throws Exception {
		return new Object[] {"nativeMe",_ref.getField(false, "_nativeme")};
}
}