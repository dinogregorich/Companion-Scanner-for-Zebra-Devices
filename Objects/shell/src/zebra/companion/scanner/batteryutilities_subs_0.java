package zebra.companion.scanner;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class batteryutilities_subs_0 {


public static RemoteObject  _class_globals(RemoteObject __ref) throws Exception{
 //BA.debugLineNum = 2;BA.debugLine="Sub Class_Globals";
 //BA.debugLineNum = 3;BA.debugLine="Private nativeMe As JavaObject";
batteryutilities._nativeme = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");__ref.setField("_nativeme",batteryutilities._nativeme);
 //BA.debugLineNum = 5;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _getbatteryinformation(RemoteObject __ref) throws Exception{
try {
		Debug.PushSubsStack("getBatteryInformation (batteryutilities) ","batteryutilities",3,__ref.getField(false, "ba"),__ref,22);
if (RapidSub.canDelegate("getbatteryinformation")) { return __ref.runUserSub(false, "batteryutilities","getbatteryinformation", __ref);}
RemoteObject _batteryinfo = null;
 BA.debugLineNum = 22;BA.debugLine="Public Sub getBatteryInformation () As Int()";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 25;BA.debugLine="Dim batteryInfo(11)  As Int";
Debug.ShouldStop(16777216);
_batteryinfo = RemoteObject.createNewArray ("int", new int[] {11}, new Object[]{});Debug.locals.put("batteryInfo", _batteryinfo);
 BA.debugLineNum = 26;BA.debugLine="batteryInfo = nativeMe.RunMethod(\"getBatteryInfor";
Debug.ShouldStop(33554432);
_batteryinfo = (__ref.getField(false,"_nativeme" /*RemoteObject*/ ).runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getBatteryInformation")),(Object)((batteryutilities.__c.getField(false,"Null")))));Debug.locals.put("batteryInfo", _batteryinfo);
 BA.debugLineNum = 27;BA.debugLine="Return batteryInfo";
Debug.ShouldStop(67108864);
if (true) return _batteryinfo;
 BA.debugLineNum = 29;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _initialize(RemoteObject __ref,RemoteObject _ba) throws Exception{
try {
		Debug.PushSubsStack("Initialize (batteryutilities) ","batteryutilities",3,__ref.getField(false, "ba"),__ref,7);
if (RapidSub.canDelegate("initialize")) { return __ref.runUserSub(false, "batteryutilities","initialize", __ref, _ba);}
__ref.runVoidMethodAndSync("innerInitializeHelper", _ba);
Debug.locals.put("ba", _ba);
 BA.debugLineNum = 7;BA.debugLine="Public Sub Initialize";
Debug.ShouldStop(64);
 BA.debugLineNum = 8;BA.debugLine="nativeMe = Me";
Debug.ShouldStop(128);
__ref.getField(false,"_nativeme" /*RemoteObject*/ ).setObject (__ref);
 BA.debugLineNum = 9;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}