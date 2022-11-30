package zebra.companion.scanner;

import android.os.BatteryManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class batteryutilities extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "zebra.companion.scanner.batteryutilities");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", zebra.companion.scanner.batteryutilities.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4j.object.JavaObject _nativeme = null;
public zebra.companion.scanner.main _main = null;
public zebra.companion.scanner.starter _starter = null;
public zebra.companion.scanner.scanner _scanner = null;
public String  _initialize(zebra.companion.scanner.batteryutilities __ref,anywheresoftware.b4a.BA _ba) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="batteryutilities";
if (Debug.shouldDelegate(ba, "initialize", false))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba}));}
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Public Sub Initialize";
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="nativeMe = Me";
__ref._nativeme /*anywheresoftware.b4j.object.JavaObject*/  = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(this));
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="End Sub";
return "";
}
public int[]  _getbatteryinformation(zebra.companion.scanner.batteryutilities __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="batteryutilities";
if (Debug.shouldDelegate(ba, "getbatteryinformation", false))
	 {return ((int[]) Debug.delegate(ba, "getbatteryinformation", null));}
int[] _batteryinfo = null;
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Public Sub getBatteryInformation () As Int()";
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="Dim batteryInfo(11)  As Int";
_batteryinfo = new int[(int) (11)];
;
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="batteryInfo = nativeMe.RunMethod(\"getBatteryInfor";
_batteryinfo = (int[])(__ref._nativeme /*anywheresoftware.b4j.object.JavaObject*/ .RunMethod("getBatteryInformation",(Object[])(__c.Null)));
RDebugUtils.currentLine=1900549;
 //BA.debugLineNum = 1900549;BA.debugLine="Return batteryInfo";
if (true) return _batteryinfo;
RDebugUtils.currentLine=1900551;
 //BA.debugLineNum = 1900551;BA.debugLine="End Sub";
return null;
}
public String  _class_globals(zebra.companion.scanner.batteryutilities __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="batteryutilities";
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="Private nativeMe As JavaObject";
_nativeme = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="End Sub";
return "";
}


  public int[] getBatteryInformation() {
    int[] mybat = new int[11];
	Intent batteryIntent = ba.context.getApplicationContext().registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        mybat[0] = level;
	int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        mybat[1] = scale;
    int health = batteryIntent.getIntExtra(BatteryManager.EXTRA_HEALTH,-1);
        mybat[2] = health; 
    int icon_small = batteryIntent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,-1);
        mybat[3] = icon_small;
    int plugged = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        mybat[4] = plugged;
//      boolean present = batteryIntent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT); 
    int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        mybat[5] = status;
//      String technology = batteryIntent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
    int temperature = batteryIntent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,-1);
        mybat[6] = temperature;
    int voltage = batteryIntent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,-1);
        mybat[7] = voltage; 
//  int ac = batteryIntent.getIntExtra("plugged",BatteryManager.BATTERY_PLUGGED_AC);
//      mybat[8] = ac; 
//  int usb = batteryIntent.getIntExtra("plugged",BatteryManager.BATTERY_PLUGGED_USB);
//      mybat[9] = usb; 

        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                     status == BatteryManager.BATTERY_STATUS_FULL;
        mybat[8] = 0;
        if (isCharging == true) {
           mybat[8] = 1;
        }   
        // How are we charging?
        mybat[9] = 0;
        mybat[10] = 0; 
        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        if (usbCharge == true) {
           mybat[9] = 2;
        }   
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        if (acCharge == true) {
           mybat[10] = 1;
        }   
        return mybat;
  }
}