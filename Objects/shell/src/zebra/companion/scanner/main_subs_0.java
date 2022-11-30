package zebra.companion.scanner;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,30);
if (RapidSub.canDelegate("activity_create")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
RemoteObject _r = RemoteObject.declareNull("anywheresoftware.b4a.agraham.reflection.Reflection");
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="Dim r As Reflector";
Debug.ShouldStop(1073741824);
_r = RemoteObject.createNew ("anywheresoftware.b4a.agraham.reflection.Reflection");Debug.locals.put("r", _r);
 BA.debugLineNum = 32;BA.debugLine="r.Target = r.GetContext";
Debug.ShouldStop(-2147483648);
_r.setField ("Target",(_r.runMethod(false,"GetContext",main.processBA)));
 BA.debugLineNum = 33;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
Debug.ShouldStop(1);
_r.setField ("Target",_r.runMethod(false,"RunMethod",(Object)(RemoteObject.createImmutable("getResources"))));
 BA.debugLineNum = 34;BA.debugLine="r.Target = r.RunMethod(\"getDisplayMetrics\")";
Debug.ShouldStop(2);
_r.setField ("Target",_r.runMethod(false,"RunMethod",(Object)(RemoteObject.createImmutable("getDisplayMetrics"))));
 BA.debugLineNum = 50;BA.debugLine="Activity.Height = 110%y";
Debug.ShouldStop(131072);
main.mostCurrent._activity.runMethod(true,"setHeight",main.mostCurrent.__c.runMethod(true,"PerYToCurrent",(Object)(BA.numberCast(float.class, 110)),main.mostCurrent.activityBA));
 BA.debugLineNum = 52;BA.debugLine="If GetDeviceLayoutValues.Width/r.GetField(\"xdpi\")";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("<",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.runMethod(false,"GetDeviceLayoutValues",main.mostCurrent.activityBA).getField(true,"Width"),BA.numberCast(double.class, _r.runMethod(false,"GetField",(Object)(RemoteObject.createImmutable("xdpi"))))}, "/",0, 0),BA.numberCast(double.class, 2))) { 
 BA.debugLineNum = 53;BA.debugLine="Activity.LoadLayout(\"main_small\")";
Debug.ShouldStop(1048576);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main_small")),main.mostCurrent.activityBA);
 }else {
 BA.debugLineNum = 55;BA.debugLine="Activity.LoadLayout(\"main_normal\")";
Debug.ShouldStop(4194304);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main_normal")),main.mostCurrent.activityBA);
 };
 BA.debugLineNum = 58;BA.debugLine="bu.Initialize";
Debug.ShouldStop(33554432);
main.mostCurrent._bu.runClassMethod (zebra.companion.scanner.batteryutilities.class, "_initialize" /*RemoteObject*/ ,main.processBA);
 BA.debugLineNum = 59;BA.debugLine="batterystatus = bu.BatteryInformation";
Debug.ShouldStop(67108864);
main._batterystatus = main.mostCurrent._bu.runClassMethod (zebra.companion.scanner.batteryutilities.class, "_getbatteryinformation" /*RemoteObject*/ );
 BA.debugLineNum = 60;BA.debugLine="bv1.Level = batterystatus(0)";
Debug.ShouldStop(134217728);
main.mostCurrent._bv1.runMethod(true,"setLevel",main._batterystatus.getArrayElement(true,BA.numberCast(int.class, 0)));
 BA.debugLineNum = 61;BA.debugLine="If batterystatus(8) = 1 Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("=",main._batterystatus.getArrayElement(true,BA.numberCast(int.class, 8)),BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 62;BA.debugLine="bv1.Charging = True";
Debug.ShouldStop(536870912);
main.mostCurrent._bv1.runVoidMethod ("setCharging",main.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 64;BA.debugLine="bv1.Charging = False";
Debug.ShouldStop(-2147483648);
main.mostCurrent._bv1.runVoidMethod ("setCharging",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 66;BA.debugLine="t.Initialize(\"t\", 20000)";
Debug.ShouldStop(2);
main._t.runVoidMethod ("Initialize",main.processBA,(Object)(BA.ObjectToString("t")),(Object)(BA.numberCast(long.class, 20000)));
 BA.debugLineNum = 67;BA.debugLine="End Sub";
Debug.ShouldStop(4);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,86);
if (RapidSub.canDelegate("activity_pause")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 86;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 87;BA.debugLine="t.Enabled = False";
Debug.ShouldStop(4194304);
main._t.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 88;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,69);
if (RapidSub.canDelegate("activity_resume")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","activity_resume");}
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _strmsg = RemoteObject.createImmutable("");
 BA.debugLineNum = 69;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(16);
 BA.debugLineNum = 70;BA.debugLine="Try";
Debug.ShouldStop(32);
try { BA.debugLineNum = 71;BA.debugLine="Dim jo As JavaObject = Activity";
Debug.ShouldStop(64);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_jo = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), main.mostCurrent._activity.getObject());Debug.locals.put("jo", _jo);Debug.locals.put("jo", _jo);
 BA.debugLineNum = 72;BA.debugLine="jo.RunMethod(\"setSystemUiVisibility\", Array As O";
Debug.ShouldStop(128);
_jo.runVoidMethod ("RunMethod",(Object)(BA.ObjectToString("setSystemUiVisibility")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {RemoteObject.createImmutable((5894))})));
 Debug.CheckDeviceExceptions();
} 
       catch (Exception e5) {
			BA.rdebugUtils.runVoidMethod("setLastException",main.processBA, e5.toString()); };
 BA.debugLineNum = 76;BA.debugLine="Dim strMsg As String = \"Press Scan Button(s) To B";
Debug.ShouldStop(2048);
_strmsg = RemoteObject.concat(RemoteObject.createImmutable("Press Scan Button(s) To Begin."),main.mostCurrent.__c.getField(true,"CRLF"),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("When finished, tap 'Save' to save the captured scans to a directory."));Debug.locals.put("strMsg", _strmsg);Debug.locals.put("strMsg", _strmsg);
 BA.debugLineNum = 77;BA.debugLine="strMsg = strMsg & CRLF & CRLF & \"A confirmation m";
Debug.ShouldStop(4096);
_strmsg = RemoteObject.concat(_strmsg,main.mostCurrent.__c.getField(true,"CRLF"),main.mostCurrent.__c.getField(true,"CRLF"),RemoteObject.createImmutable("A confirmation message will appear when successfully saved."));Debug.locals.put("strMsg", _strmsg);
 BA.debugLineNum = 78;BA.debugLine="label_scanResult.Text = strMsg";
Debug.ShouldStop(8192);
main.mostCurrent._label_scanresult.runMethod(true,"setText",BA.ObjectToCharSequence(_strmsg));
 BA.debugLineNum = 79;BA.debugLine="label_scanResult.TextColor = Colors.ARGB(255, 22,";
Debug.ShouldStop(16384);
main.mostCurrent._label_scanresult.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"ARGB",(Object)(BA.numberCast(int.class, 255)),(Object)(BA.numberCast(int.class, 22)),(Object)(BA.numberCast(int.class, 22)),(Object)(BA.numberCast(int.class, 22))));
 BA.debugLineNum = 80;BA.debugLine="t.Enabled = True";
Debug.ShouldStop(32768);
main._t.runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 81;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Tr";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",main.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("SCANS.tmp"))),main.mostCurrent.__c.getField(true,"True"))) { 
 BA.debugLineNum = 82;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
Debug.ShouldStop(131072);
_findviewbytag(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._activity.getObject()),RemoteObject.createImmutable("AppSave")).runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 };
 BA.debugLineNum = 84;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static void  _buttonsave_click() throws Exception{
try {
		Debug.PushSubsStack("ButtonSave_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,107);
if (RapidSub.canDelegate("buttonsave_click")) { zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","buttonsave_click"); return;}
ResumableSub_ButtonSave_Click rsub = new ResumableSub_ButtonSave_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_ButtonSave_Click extends BA.ResumableSub {
public ResumableSub_ButtonSave_Click(zebra.companion.scanner.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
zebra.companion.scanner.main parent;
RemoteObject _strfilename = RemoteObject.createImmutable("");
RemoteObject _success = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("ButtonSave_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,107);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 108;BA.debugLine="DateTime.DateFormat=\"yyMMdd-HHmmss\"";
Debug.ShouldStop(2048);
parent.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setDateFormat",BA.ObjectToString("yyMMdd-HHmmss"));
 BA.debugLineNum = 109;BA.debugLine="Dim strFileName As String = \"scans_\" & DateTime.D";
Debug.ShouldStop(4096);
_strfilename = RemoteObject.concat(RemoteObject.createImmutable("scans_"),parent.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Date",(Object)(parent.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))),RemoteObject.createImmutable(".csv"));Debug.locals.put("strFileName", _strfilename);Debug.locals.put("strFileName", _strfilename);
 BA.debugLineNum = 110;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirInternal,";
Debug.ShouldStop(8192);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","complete", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "buttonsave_click"), _saveas(parent.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenInput",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("SCANS.tmp"))),BA.ObjectToString("application/octet-stream"),_strfilename));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Success", _success);
;
 BA.debugLineNum = 111;BA.debugLine="Log(\"File saved successfully? \" & Success)";
Debug.ShouldStop(16384);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","2393220",RemoteObject.concat(RemoteObject.createImmutable("File saved successfully? "),_success),0);
 BA.debugLineNum = 112;BA.debugLine="If Success = True Then";
Debug.ShouldStop(32768);
if (true) break;

case 1:
//if
this.state = 6;
if (RemoteObject.solveBoolean("=",_success,parent.mostCurrent.__c.getField(true,"True"))) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
 BA.debugLineNum = 113;BA.debugLine="File.Delete(File.DirInternal,\"SCANS.tmp\")";
Debug.ShouldStop(65536);
parent.mostCurrent.__c.getField(false,"File").runVoidMethod ("Delete",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("SCANS.tmp")));
 BA.debugLineNum = 114;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = Fals";
Debug.ShouldStop(131072);
_findviewbytag(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), parent.mostCurrent._activity.getObject()),RemoteObject.createImmutable("AppSave")).runMethod(true,"setEnabled",parent.mostCurrent.__c.getField(true,"False"));
 if (true) break;

case 5:
//C
this.state = 6;
 BA.debugLineNum = 116;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("File did not save.  Try again")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Write File Error"))),main.processBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
 BA.debugLineNum = 118;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _complete(RemoteObject _success) throws Exception{
}
public static RemoteObject  _findviewbytag(RemoteObject _parent,RemoteObject _name) throws Exception{
try {
		Debug.PushSubsStack("FindViewByTag (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,200);
if (RapidSub.canDelegate("findviewbytag")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","findviewbytag", _parent, _name);}
RemoteObject _v = RemoteObject.declareNull("anywheresoftware.b4a.objects.ConcreteViewWrapper");
Debug.locals.put("Parent", _parent);
Debug.locals.put("Name", _name);
 BA.debugLineNum = 200;BA.debugLine="Sub FindViewByTag(Parent As Panel, Name As String)";
Debug.ShouldStop(128);
 BA.debugLineNum = 201;BA.debugLine="For Each v As View In Parent.GetAllViewsRecursive";
Debug.ShouldStop(256);
_v = RemoteObject.createNew ("anywheresoftware.b4a.objects.ConcreteViewWrapper");
{
final RemoteObject group1 = _parent.runMethod(false,"GetAllViewsRecursive");
final int groupLen1 = group1.runMethod(true,"getSize").<Integer>get()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), group1.runMethod(false,"Get",index1));Debug.locals.put("v", _v);
Debug.locals.put("v", _v);
 BA.debugLineNum = 202;BA.debugLine="If Name = v.Tag Then Return v";
Debug.ShouldStop(512);
if (RemoteObject.solveBoolean("=",_name,BA.ObjectToString(_v.runMethod(false,"getTag")))) { 
if (true) return _v;};
 }
}Debug.locals.put("v", _v);
;
 BA.debugLineNum = 204;BA.debugLine="Log(\"View not found: \" & Name)";
Debug.ShouldStop(2048);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2983044",RemoteObject.concat(RemoteObject.createImmutable("View not found: "),_name),0);
 BA.debugLineNum = 205;BA.debugLine="Return Null";
Debug.ShouldStop(4096);
if (true) return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ConcreteViewWrapper"), main.mostCurrent.__c.getField(false,"Null"));
 BA.debugLineNum = 206;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _getba() throws Exception{
try {
		Debug.PushSubsStack("GetBA (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,149);
if (RapidSub.canDelegate("getba")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","getba");}
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _cls = RemoteObject.createImmutable("");
 BA.debugLineNum = 149;BA.debugLine="Sub GetBA As Object";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 150;BA.debugLine="Dim jo As JavaObject";
Debug.ShouldStop(2097152);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("jo", _jo);
 BA.debugLineNum = 151;BA.debugLine="Dim cls As String = Me";
Debug.ShouldStop(4194304);
_cls = BA.ObjectToString(main.getObject());Debug.locals.put("cls", _cls);Debug.locals.put("cls", _cls);
 BA.debugLineNum = 152;BA.debugLine="cls = cls.SubString(\"class \".Length)";
Debug.ShouldStop(8388608);
_cls = _cls.runMethod(true,"substring",(Object)(RemoteObject.createImmutable("class ").runMethod(true,"length")));Debug.locals.put("cls", _cls);
 BA.debugLineNum = 153;BA.debugLine="jo.InitializeStatic(cls)";
Debug.ShouldStop(16777216);
_jo.runVoidMethod ("InitializeStatic",(Object)(_cls));
 BA.debugLineNum = 154;BA.debugLine="Return jo.GetField(\"processBA\")";
Debug.ShouldStop(33554432);
if (true) return _jo.runMethod(false,"GetField",(Object)(RemoteObject.createImmutable("processBA")));
 BA.debugLineNum = 155;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable(null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private bv1 As BatteryView";
main.mostCurrent._bv1 = RemoteObject.createNew ("batteryviewwrapper.batteryviewWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private bu As BatteryUtilities";
main.mostCurrent._bu = RemoteObject.createNew ("zebra.companion.scanner.batteryutilities");
 //BA.debugLineNum = 26;BA.debugLine="Dim batterystatus(11) As Int";
main._batterystatus = RemoteObject.createNewArray ("int", new int[] {11}, new Object[]{});
 //BA.debugLineNum = 27;BA.debugLine="Private label_scanResult As Label";
main.mostCurrent._label_scanresult = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static void  _imageview1_click() throws Exception{
try {
		Debug.PushSubsStack("ImageView1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,179);
if (RapidSub.canDelegate("imageview1_click")) { zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","imageview1_click"); return;}
ResumableSub_ImageView1_Click rsub = new ResumableSub_ImageView1_Click(null);
rsub.resume(null, null);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_ImageView1_Click extends BA.ResumableSub {
public ResumableSub_ImageView1_Click(zebra.companion.scanner.main parent) {
this.parent = parent;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
zebra.companion.scanner.main parent;
RemoteObject _result = RemoteObject.createImmutable(0);
RemoteObject _strfilename = RemoteObject.createImmutable("");
RemoteObject _success = RemoteObject.createImmutable(false);

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("ImageView1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,179);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 BA.debugLineNum = 180;BA.debugLine="MsgboxAsync(\"Copy DataWedge profile to a location";
Debug.ShouldStop(524288);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Copy DataWedge profile to a location such as the downloads directory. Then import profile into DataWedge.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("DataWedge Profile"))),main.processBA);
 BA.debugLineNum = 181;BA.debugLine="Wait For MsgBox_Result (Result As Int)";
Debug.ShouldStop(1048576);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","msgbox_result", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "imageview1_click"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Result", _result);
;
 BA.debugLineNum = 182;BA.debugLine="Dim strFileName As String = \"dwprofile_ZebraCompa";
Debug.ShouldStop(2097152);
_strfilename = BA.ObjectToString("dwprofile_ZebraCompanionScanner_DW6.db");Debug.locals.put("strFileName", _strfilename);Debug.locals.put("strFileName", _strfilename);
 BA.debugLineNum = 183;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirAssets,st";
Debug.ShouldStop(4194304);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","complete", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "imageview1_click"), _saveas(parent.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenInput",(Object)(parent.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(_strfilename)),BA.ObjectToString("application/x-sqlite3"),_strfilename));
this.state = 6;
return;
case 6:
//C
this.state = 1;
_success = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("Success", _success);
;
 BA.debugLineNum = 184;BA.debugLine="Log(\"File saved successfully? \" & Success)";
Debug.ShouldStop(8388608);
parent.mostCurrent.__c.runVoidMethod ("LogImpl","2851973",RemoteObject.concat(RemoteObject.createImmutable("File saved successfully? "),_success),0);
 BA.debugLineNum = 185;BA.debugLine="If Success = False Then";
Debug.ShouldStop(16777216);
if (true) break;

case 1:
//if
this.state = 4;
if (RemoteObject.solveBoolean("=",_success,parent.mostCurrent.__c.getField(true,"False"))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 186;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
Debug.ShouldStop(33554432);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("File did not save.  Try again")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Write File Error"))),main.processBA);
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _msgbox_result(RemoteObject _result) throws Exception{
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
scanner_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("zebra.companion.scanner.main");
starter.myClass = BA.getDeviceClass ("zebra.companion.scanner.starter");
scanner.myClass = BA.getDeviceClass ("zebra.companion.scanner.scanner");
batteryutilities.myClass = BA.getDeviceClass ("zebra.companion.scanner.batteryutilities");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Dim t As Timer";
main._t = RemoteObject.createNew ("anywheresoftware.b4a.objects.Timer");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _processscan(RemoteObject _s) throws Exception{
try {
		Debug.PushSubsStack("ProcessScan (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,90);
if (RapidSub.canDelegate("processscan")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","processscan", _s);}
RemoteObject _writer = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.TextWriterWrapper");
Debug.locals.put("s", _s);
 BA.debugLineNum = 90;BA.debugLine="Sub ProcessScan (s As String)";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 91;BA.debugLine="Log(s)";
Debug.ShouldStop(67108864);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2327681",_s,0);
 BA.debugLineNum = 92;BA.debugLine="label_scanResult.Text = s.Replace(\",\",CRLF)";
Debug.ShouldStop(134217728);
main.mostCurrent._label_scanresult.runMethod(true,"setText",BA.ObjectToCharSequence(_s.runMethod(true,"replace",(Object)(BA.ObjectToString(",")),(Object)(main.mostCurrent.__c.getField(true,"CRLF")))));
 BA.debugLineNum = 93;BA.debugLine="Dim Writer As TextWriter";
Debug.ShouldStop(268435456);
_writer = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.TextWriterWrapper");Debug.locals.put("Writer", _writer);
 BA.debugLineNum = 94;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Fa";
Debug.ShouldStop(536870912);
if (RemoteObject.solveBoolean("=",main.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("SCANS.tmp"))),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 95;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
Debug.ShouldStop(1073741824);
_writer.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenOutput",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("SCANS.tmp")),(Object)(main.mostCurrent.__c.getField(true,"True"))).getObject())));
 BA.debugLineNum = 96;BA.debugLine="Writer.WriteLine(Chr(34) & \"Date/Time\" & Chr(34)";
Debug.ShouldStop(-2147483648);
_writer.runVoidMethod ("WriteLine",(Object)(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable("Date/Time"),main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable(","),main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable("Symbology"),main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable(","),main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable("Value"),main.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))))));
 BA.debugLineNum = 97;BA.debugLine="Writer.WriteLine(s)";
Debug.ShouldStop(1);
_writer.runVoidMethod ("WriteLine",(Object)(_s));
 BA.debugLineNum = 98;BA.debugLine="Writer.Close";
Debug.ShouldStop(2);
_writer.runVoidMethod ("Close");
 }else {
 BA.debugLineNum = 100;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
Debug.ShouldStop(8);
_writer.runVoidMethod ("Initialize",(Object)((main.mostCurrent.__c.getField(false,"File").runMethod(false,"OpenOutput",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("SCANS.tmp")),(Object)(main.mostCurrent.__c.getField(true,"True"))).getObject())));
 BA.debugLineNum = 101;BA.debugLine="Writer.WriteLine(s)";
Debug.ShouldStop(16);
_writer.runVoidMethod ("WriteLine",(Object)(_s));
 BA.debugLineNum = 102;BA.debugLine="Writer.Close";
Debug.ShouldStop(32);
_writer.runVoidMethod ("Close");
 };
 BA.debugLineNum = 104;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
Debug.ShouldStop(128);
_findviewbytag(RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._activity.getObject()),RemoteObject.createImmutable("AppSave")).runMethod(true,"setEnabled",main.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 105;BA.debugLine="End Sub";
Debug.ShouldStop(256);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _saveas(RemoteObject _source,RemoteObject _mimetype,RemoteObject _title) throws Exception{
try {
		Debug.PushSubsStack("SaveAs (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
if (RapidSub.canDelegate("saveas")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","saveas", _source, _mimetype, _title);}
ResumableSub_SaveAs rsub = new ResumableSub_SaveAs(null,_source,_mimetype,_title);
rsub.remoteResumableSub = anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSubForFilter();
rsub.resume(null, null);
return RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.keywords.Common.ResumableSubWrapper"), rsub.remoteResumableSub);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static class ResumableSub_SaveAs extends BA.ResumableSub {
public ResumableSub_SaveAs(zebra.companion.scanner.main parent,RemoteObject _source,RemoteObject _mimetype,RemoteObject _title) {
this.parent = parent;
this._source = _source;
this._mimetype = _mimetype;
this._title = _title;
}
java.util.LinkedHashMap<String, Object> rsLocals = new java.util.LinkedHashMap<String, Object>();
zebra.companion.scanner.main parent;
RemoteObject _source;
RemoteObject _mimetype;
RemoteObject _title;
RemoteObject _intent = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");
RemoteObject _methodname = RemoteObject.createImmutable("");
RemoteObject _args = null;
RemoteObject _result = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _ctxt = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _out = RemoteObject.declareNull("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");

@Override
public void resume(BA ba, RemoteObject result) throws Exception{
try {
		Debug.PushSubsStack("SaveAs (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,120);
Debug.locals = rsLocals;Debug.currentSubFrame.locals = rsLocals;

    while (true) {
        switch (state) {
            case -1:
{
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,RemoteObject.createImmutable(null));return;}
case 0:
//C
this.state = 1;
Debug.locals.put("Source", _source);
Debug.locals.put("MimeType", _mimetype);
Debug.locals.put("Title", _title);
 BA.debugLineNum = 121;BA.debugLine="Dim intent As Intent";
Debug.ShouldStop(16777216);
_intent = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("intent", _intent);
 BA.debugLineNum = 122;BA.debugLine="intent.Initialize(\"android.intent.action.CREATE_D";
Debug.ShouldStop(33554432);
_intent.runVoidMethod ("Initialize",(Object)(BA.ObjectToString("android.intent.action.CREATE_DOCUMENT")),(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 123;BA.debugLine="intent.AddCategory(\"android.intent.category.OPENA";
Debug.ShouldStop(67108864);
_intent.runVoidMethod ("AddCategory",(Object)(RemoteObject.createImmutable("android.intent.category.OPENABLE")));
 BA.debugLineNum = 124;BA.debugLine="intent.PutExtra(\"android.intent.extra.TITLE\", Tit";
Debug.ShouldStop(134217728);
_intent.runVoidMethod ("PutExtra",(Object)(BA.ObjectToString("android.intent.extra.TITLE")),(Object)((_title)));
 BA.debugLineNum = 125;BA.debugLine="intent.SetType(MimeType)";
Debug.ShouldStop(268435456);
_intent.runVoidMethod ("SetType",(Object)(_mimetype));
 BA.debugLineNum = 126;BA.debugLine="StartActivityForResult(intent)";
Debug.ShouldStop(536870912);
_startactivityforresult(_intent);
 BA.debugLineNum = 127;BA.debugLine="Wait For ion_Event (MethodName As String, Args()";
Debug.ShouldStop(1073741824);
parent.mostCurrent.__c.runVoidMethod ("WaitFor","ion_event", main.processBA, anywheresoftware.b4a.pc.PCResumableSub.createDebugResumeSub(this, "main", "saveas"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_methodname = (RemoteObject) result.getArrayElement(true,RemoteObject.createImmutable(0));Debug.locals.put("MethodName", _methodname);
_args = (RemoteObject) result.getArrayElement(false,RemoteObject.createImmutable(1));Debug.locals.put("Args", _args);
;
 BA.debugLineNum = 128;BA.debugLine="If -1 = Args(0) Then";
Debug.ShouldStop(-2147483648);
if (true) break;

case 1:
//if
this.state = 4;
if (RemoteObject.solveBoolean("=",RemoteObject.createImmutable(-(double) (0 + 1)),BA.numberCast(double.class, _args.getArrayElement(false,BA.numberCast(int.class, 0))))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
 BA.debugLineNum = 130;BA.debugLine="Dim result As Intent = Args(1)";
Debug.ShouldStop(2);
_result = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");
_result = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.IntentWrapper"), _args.getArrayElement(false,BA.numberCast(int.class, 1)));Debug.locals.put("result", _result);Debug.locals.put("result", _result);
 BA.debugLineNum = 131;BA.debugLine="Dim jo As JavaObject = result";
Debug.ShouldStop(4);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_jo = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _result.getObject());Debug.locals.put("jo", _jo);Debug.locals.put("jo", _jo);
 BA.debugLineNum = 132;BA.debugLine="Dim ctxt As JavaObject";
Debug.ShouldStop(8);
_ctxt = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("ctxt", _ctxt);
 BA.debugLineNum = 133;BA.debugLine="Dim out As OutputStream = ctxt.InitializeContext";
Debug.ShouldStop(16);
_out = RemoteObject.createNew ("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper");
_out = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper"), _ctxt.runMethod(false,"InitializeContext",main.processBA).runMethod(false,"RunMethodJO",(Object)(BA.ObjectToString("getContentResolver")),(Object)((parent.mostCurrent.__c.getField(false,"Null")))).runMethod(false,"RunMethod",(Object)(BA.ObjectToString("openOutputStream")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {_jo.runMethod(false,"RunMethod",(Object)(BA.ObjectToString("getData")),(Object)((parent.mostCurrent.__c.getField(false,"Null"))))}))));Debug.locals.put("out", _out);Debug.locals.put("out", _out);
 BA.debugLineNum = 134;BA.debugLine="File.Copy2(Source, out)";
Debug.ShouldStop(32);
parent.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy2",(Object)((_source.getObject())),(Object)((_out.getObject())));
 BA.debugLineNum = 135;BA.debugLine="out.Close";
Debug.ShouldStop(64);
_out.runVoidMethod ("Close");
 BA.debugLineNum = 136;BA.debugLine="MsgboxAsync(\"File \" & Title & \" was saved succes";
Debug.ShouldStop(128);
parent.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("File "),_title,RemoteObject.createImmutable(" was saved successfully")))),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Write File Success"))),main.processBA);
 BA.debugLineNum = 137;BA.debugLine="Return True";
Debug.ShouldStop(256);
if (true) {
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.mostCurrent.__c.getField(true,"True")));return;};
 if (true) break;

case 4:
//C
this.state = -1;
;
 BA.debugLineNum = 139;BA.debugLine="Return False";
Debug.ShouldStop(1024);
if (true) {
parent.mostCurrent.__c.runVoidMethod ("ReturnFromResumableSub",this.remoteResumableSub,(parent.mostCurrent.__c.getField(true,"False")));return;};
 BA.debugLineNum = 140;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
if (true) break;

            }
        }
    }
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}
public static void  _ion_event(RemoteObject _methodname,RemoteObject _args) throws Exception{
}
public static RemoteObject  _scanadd() throws Exception{
try {
		Debug.PushSubsStack("ScanAdd (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,157);
if (RapidSub.canDelegate("scanadd")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","scanadd");}
RemoteObject _softscantrigger = RemoteObject.createImmutable("");
RemoteObject _extradata = RemoteObject.createImmutable("");
RemoteObject _jobj = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _iobj = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");
 BA.debugLineNum = 157;BA.debugLine="Sub ScanAdd";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="Dim softScanTrigger As String = \"com.symbol.dataw";
Debug.ShouldStop(536870912);
_softscantrigger = BA.ObjectToString("com.symbol.datawedge.api.ACTION");Debug.locals.put("softScanTrigger", _softscantrigger);Debug.locals.put("softScanTrigger", _softscantrigger);
 BA.debugLineNum = 159;BA.debugLine="Dim extraData As String = \"com.symbol.datawedge.a";
Debug.ShouldStop(1073741824);
_extradata = BA.ObjectToString("com.symbol.datawedge.api.SOFT_SCAN_TRIGGER");Debug.locals.put("extraData", _extradata);Debug.locals.put("extraData", _extradata);
 BA.debugLineNum = 160;BA.debugLine="Dim jObj As JavaObject";
Debug.ShouldStop(-2147483648);
_jobj = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");Debug.locals.put("jObj", _jobj);
 BA.debugLineNum = 161;BA.debugLine="jObj.InitializeContext";
Debug.ShouldStop(1);
_jobj.runVoidMethod ("InitializeContext",main.processBA);
 BA.debugLineNum = 162;BA.debugLine="Dim iObj As Intent";
Debug.ShouldStop(2);
_iobj = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");Debug.locals.put("iObj", _iobj);
 BA.debugLineNum = 163;BA.debugLine="iObj.Initialize(softScanTrigger,\"\")";
Debug.ShouldStop(4);
_iobj.runVoidMethod ("Initialize",(Object)(_softscantrigger),(Object)(RemoteObject.createImmutable("")));
 BA.debugLineNum = 164;BA.debugLine="iObj.PutExtra(extraData, \"START_SCANNING\")";
Debug.ShouldStop(8);
_iobj.runVoidMethod ("PutExtra",(Object)(_extradata),(Object)((RemoteObject.createImmutable("START_SCANNING"))));
 BA.debugLineNum = 165;BA.debugLine="jObj.RunMethod(\"sendBroadcast\", Array(iObj))";
Debug.ShouldStop(16);
_jobj.runVoidMethod ("RunMethod",(Object)(BA.ObjectToString("sendBroadcast")),(Object)(RemoteObject.createNewArray("Object",new int[] {1},new Object[] {(_iobj.getObject())})));
 BA.debugLineNum = 166;BA.debugLine="Log(\"Broadcast Sent\")";
Debug.ShouldStop(32);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2655369",RemoteObject.createImmutable("Broadcast Sent"),0);
 BA.debugLineNum = 167;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _scanadd_click() throws Exception{
try {
		Debug.PushSubsStack("ScanAdd_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,169);
if (RapidSub.canDelegate("scanadd_click")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","scanadd_click");}
 BA.debugLineNum = 169;BA.debugLine="Sub ScanAdd_Click";
Debug.ShouldStop(256);
 BA.debugLineNum = 170;BA.debugLine="Log(\"Short Click\")";
Debug.ShouldStop(512);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2720897",RemoteObject.createImmutable("Short Click"),0);
 BA.debugLineNum = 171;BA.debugLine="CallSub(Me, ScanAdd)";
Debug.ShouldStop(1024);
main.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",main.processBA,(Object)(main.getObject()),(Object)(_scanadd()));
 BA.debugLineNum = 172;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _scanadd_longclick() throws Exception{
try {
		Debug.PushSubsStack("ScanAdd_LongClick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,174);
if (RapidSub.canDelegate("scanadd_longclick")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","scanadd_longclick");}
 BA.debugLineNum = 174;BA.debugLine="Private Sub ScanAdd_LongClick";
Debug.ShouldStop(8192);
 BA.debugLineNum = 175;BA.debugLine="Log(\"Long Click\")";
Debug.ShouldStop(16384);
main.mostCurrent.__c.runVoidMethod ("LogImpl","2786433",RemoteObject.createImmutable("Long Click"),0);
 BA.debugLineNum = 176;BA.debugLine="CallSub(Me, ScanAdd)";
Debug.ShouldStop(32768);
main.mostCurrent.__c.runMethodAndSync(false,"CallSubNew",main.processBA,(Object)(main.getObject()),(Object)(_scanadd()));
 BA.debugLineNum = 177;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _startactivityforresult(RemoteObject _i) throws Exception{
try {
		Debug.PushSubsStack("StartActivityForResult (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,142);
if (RapidSub.canDelegate("startactivityforresult")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","startactivityforresult", _i);}
RemoteObject _jo = RemoteObject.declareNull("anywheresoftware.b4j.object.JavaObject");
RemoteObject _ion = RemoteObject.declareNull("Object");
Debug.locals.put("i", _i);
 BA.debugLineNum = 142;BA.debugLine="Sub StartActivityForResult(i As Intent)";
Debug.ShouldStop(8192);
 BA.debugLineNum = 143;BA.debugLine="Dim jo As JavaObject = GetBA";
Debug.ShouldStop(16384);
_jo = RemoteObject.createNew ("anywheresoftware.b4j.object.JavaObject");
_jo = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4j.object.JavaObject"), _getba());Debug.locals.put("jo", _jo);Debug.locals.put("jo", _jo);
 BA.debugLineNum = 144;BA.debugLine="Dim ion As Object";
Debug.ShouldStop(32768);
_ion = RemoteObject.createNew ("Object");Debug.locals.put("ion", _ion);
 BA.debugLineNum = 145;BA.debugLine="ion = jo.CreateEvent(\"anywheresoftware.b4a.IOnAct";
Debug.ShouldStop(65536);
_ion = _jo.runMethod(false,"CreateEvent",main.processBA,(Object)(BA.ObjectToString("anywheresoftware.b4a.IOnActivityResult")),(Object)(BA.ObjectToString("ion")),(Object)(main.mostCurrent.__c.getField(false,"Null")));Debug.locals.put("ion", _ion);
 BA.debugLineNum = 146;BA.debugLine="jo.RunMethod(\"startActivityForResult\", Array(ion,";
Debug.ShouldStop(131072);
_jo.runVoidMethod ("RunMethod",(Object)(BA.ObjectToString("startActivityForResult")),(Object)(RemoteObject.createNewArray("Object",new int[] {2},new Object[] {_ion,(_i.getObject())})));
 BA.debugLineNum = 147;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _t_tick() throws Exception{
try {
		Debug.PushSubsStack("t_tick (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,190);
if (RapidSub.canDelegate("t_tick")) { return zebra.companion.scanner.main.remoteMe.runUserSub(false, "main","t_tick");}
 BA.debugLineNum = 190;BA.debugLine="Sub t_tick";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 191;BA.debugLine="batterystatus = bu.BatteryInformation";
Debug.ShouldStop(1073741824);
main._batterystatus = main.mostCurrent._bu.runClassMethod (zebra.companion.scanner.batteryutilities.class, "_getbatteryinformation" /*RemoteObject*/ );
 BA.debugLineNum = 192;BA.debugLine="bv1.Level = batterystatus(0)";
Debug.ShouldStop(-2147483648);
main.mostCurrent._bv1.runMethod(true,"setLevel",main._batterystatus.getArrayElement(true,BA.numberCast(int.class, 0)));
 BA.debugLineNum = 193;BA.debugLine="If batterystatus(8) = 1 Then";
Debug.ShouldStop(1);
if (RemoteObject.solveBoolean("=",main._batterystatus.getArrayElement(true,BA.numberCast(int.class, 8)),BA.numberCast(double.class, 1))) { 
 BA.debugLineNum = 194;BA.debugLine="bv1.Charging = True";
Debug.ShouldStop(2);
main.mostCurrent._bv1.runVoidMethod ("setCharging",main.mostCurrent.__c.getField(true,"True"));
 }else {
 BA.debugLineNum = 196;BA.debugLine="bv1.Charging = False";
Debug.ShouldStop(8);
main.mostCurrent._bv1.runVoidMethod ("setCharging",main.mostCurrent.__c.getField(true,"False"));
 };
 BA.debugLineNum = 198;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}