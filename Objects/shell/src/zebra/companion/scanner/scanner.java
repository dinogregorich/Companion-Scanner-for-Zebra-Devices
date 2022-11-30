
package zebra.companion.scanner;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class scanner implements IRemote{
	public static scanner mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public scanner() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
public boolean isSingleton() {
		return true;
	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("scanner"), "zebra.companion.scanner.scanner");
	}
     public static RemoteObject getObject() {
		return myClass;
	 }
	public RemoteObject _service;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
        _service = (RemoteObject) args[2];
        remoteMe = RemoteObject.declareNull("zebra.companion.scanner.scanner");
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[3];
		pcBA = new PCBA(this, scanner.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _bc = RemoteObject.declareNull("com.rootsoft.broadcastreceiver.BroadCastReceiver");
public static RemoteObject _structure = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _data = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.Map");
public static zebra.companion.scanner.main _main = null;
public static zebra.companion.scanner.starter _starter = null;
  public Object[] GetGlobals() {
		return new Object[] {"BC",scanner._bc,"data",scanner._data,"Main",Debug.moduleToString(zebra.companion.scanner.main.class),"Service",scanner.mostCurrent._service,"Starter",Debug.moduleToString(zebra.companion.scanner.starter.class),"structure",scanner._structure};
}
}