package zebra.companion.scanner;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class scanner_subs_0 {


public static RemoteObject  _bc_onreceive(RemoteObject _action,RemoteObject _o) throws Exception{
try {
		Debug.PushSubsStack("BC_OnReceive (scanner) ","scanner",2,scanner.processBA,scanner.mostCurrent,45);
if (RapidSub.canDelegate("bc_onreceive")) { return zebra.companion.scanner.scanner.remoteMe.runUserSub(false, "scanner","bc_onreceive", _action, _o);}
RemoteObject _intent = RemoteObject.declareNull("anywheresoftware.b4a.objects.IntentWrapper");
RemoteObject _l = RemoteObject.createImmutable("");
RemoteObject _strsym = RemoteObject.createImmutable("");
RemoteObject _strall = RemoteObject.createImmutable("");
Debug.locals.put("action", _action);
Debug.locals.put("o", _o);
 BA.debugLineNum = 45;BA.debugLine="Sub BC_OnReceive (action As String, o As Object)";
Debug.ShouldStop(4096);
 BA.debugLineNum = 47;BA.debugLine="Dim intent As Intent = o";
Debug.ShouldStop(16384);
_intent = RemoteObject.createNew ("anywheresoftware.b4a.objects.IntentWrapper");
_intent = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.IntentWrapper"), _o);Debug.locals.put("intent", _intent);Debug.locals.put("intent", _intent);
 BA.debugLineNum = 48;BA.debugLine="data.Initialize()";
Debug.ShouldStop(32768);
scanner._data.runVoidMethod ("Initialize");
 BA.debugLineNum = 49;BA.debugLine="For Each l As String In structure";
Debug.ShouldStop(65536);
{
final RemoteObject group3 = scanner._structure;
final int groupLen3 = group3.runMethod(true,"getSize").<Integer>get()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_l = BA.ObjectToString(group3.runMethod(false,"Get",index3));Debug.locals.put("l", _l);
Debug.locals.put("l", _l);
 BA.debugLineNum = 50;BA.debugLine="If intent.HasExtra(l) Then";
Debug.ShouldStop(131072);
if (_intent.runMethod(true,"HasExtra",(Object)(_l)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 51;BA.debugLine="data.Put(l.SubString(l.LastIndexOf(\".\")+1), int";
Debug.ShouldStop(262144);
scanner._data.runVoidMethod ("Put",(Object)((_l.runMethod(true,"substring",(Object)(RemoteObject.solve(new RemoteObject[] {_l.runMethod(true,"lastIndexOf",(Object)(RemoteObject.createImmutable("."))),RemoteObject.createImmutable(1)}, "+",1, 1))))),(Object)(_intent.runMethod(false,"GetExtra",(Object)(_l))));
 };
 }
}Debug.locals.put("l", _l);
;
 BA.debugLineNum = 54;BA.debugLine="Dim strSym As String = data.Get(\"label_type\")";
Debug.ShouldStop(2097152);
_strsym = BA.ObjectToString(scanner._data.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("label_type")))));Debug.locals.put("strSym", _strsym);Debug.locals.put("strSym", _strsym);
 BA.debugLineNum = 55;BA.debugLine="strSym = strSym.ToUpperCase";
Debug.ShouldStop(4194304);
_strsym = _strsym.runMethod(true,"toUpperCase");Debug.locals.put("strSym", _strsym);
 BA.debugLineNum = 56;BA.debugLine="strSym = strSym.Replace(\"LABEL-TYPE-\",\"\")";
Debug.ShouldStop(8388608);
_strsym = _strsym.runMethod(true,"replace",(Object)(BA.ObjectToString("LABEL-TYPE-")),(Object)(RemoteObject.createImmutable("")));Debug.locals.put("strSym", _strsym);
 BA.debugLineNum = 57;BA.debugLine="DateTime.DateFormat=\"yyyy-MM-dd HH:mm:ss\"";
Debug.ShouldStop(16777216);
scanner.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setDateFormat",BA.ObjectToString("yyyy-MM-dd HH:mm:ss"));
 BA.debugLineNum = 58;BA.debugLine="Dim strAll As String = Chr(34) & DateTime.Date(Da";
Debug.ShouldStop(33554432);
_strall = RemoteObject.concat(scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),scanner.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Date",(Object)(scanner.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow"))),scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable(","),scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),_strsym,scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),RemoteObject.createImmutable(","),scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))),scanner._data.runMethod(false,"Get",(Object)((RemoteObject.createImmutable("data_string")))),scanner.mostCurrent.__c.runMethod(true,"Chr",(Object)(BA.numberCast(int.class, 34))));Debug.locals.put("strAll", _strall);Debug.locals.put("strAll", _strall);
 BA.debugLineNum = 60;BA.debugLine="CallSubDelayed2(Main, \"ProcessScan\", strAll)";
Debug.ShouldStop(134217728);
scanner.mostCurrent.__c.runVoidMethod ("CallSubDelayed2",scanner.processBA,(Object)((scanner.mostCurrent._main.getObject())),(Object)(BA.ObjectToString("ProcessScan")),(Object)((_strall)));
 BA.debugLineNum = 61;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim BC As BroadCastReceiver";
scanner._bc = RemoteObject.createNew ("com.rootsoft.broadcastreceiver.BroadCastReceiver");
 //BA.debugLineNum = 10;BA.debugLine="Dim structure As List";
scanner._structure = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 11;BA.debugLine="Dim data As Map";
scanner._data = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.Map");
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _service_create() throws Exception{
try {
		Debug.PushSubsStack("Service_Create (scanner) ","scanner",2,scanner.processBA,scanner.mostCurrent,14);
if (RapidSub.canDelegate("service_create")) { return zebra.companion.scanner.scanner.remoteMe.runUserSub(false, "scanner","service_create");}
 BA.debugLineNum = 14;BA.debugLine="Sub Service_Create";
Debug.ShouldStop(8192);
 BA.debugLineNum = 16;BA.debugLine="BC.Initialize(\"BC\")";
Debug.ShouldStop(32768);
scanner._bc.runVoidMethod ("Initialize",scanner.processBA,(Object)(RemoteObject.createImmutable("BC")));
 BA.debugLineNum = 17;BA.debugLine="structure.Initialize()";
Debug.ShouldStop(65536);
scanner._structure.runVoidMethod ("Initialize");
 BA.debugLineNum = 18;BA.debugLine="structure.Add(\"com.symbol.datawedge.source\")";
Debug.ShouldStop(131072);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.source"))));
 BA.debugLineNum = 19;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_string\")";
Debug.ShouldStop(262144);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.data_string"))));
 BA.debugLineNum = 20;BA.debugLine="structure.Add(\"com.symbol.datawedge.label_type\")";
Debug.ShouldStop(524288);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.label_type"))));
 BA.debugLineNum = 21;BA.debugLine="structure.Add(\"com.symbol.datawedge.decode_data\")";
Debug.ShouldStop(1048576);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.decode_data"))));
 BA.debugLineNum = 22;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_dispatch";
Debug.ShouldStop(2097152);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.data_dispatch_time"))));
 BA.debugLineNum = 23;BA.debugLine="structure.Add(\"com.symbol.datawedge.decoded_mode\"";
Debug.ShouldStop(4194304);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.decoded_mode"))));
 BA.debugLineNum = 24;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
Debug.ShouldStop(8388608);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.motorolasolutions.emdk.datawedge.source"))));
 BA.debugLineNum = 25;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
Debug.ShouldStop(16777216);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.motorolasolutions.emdk.datawedge.data_string"))));
 BA.debugLineNum = 26;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
Debug.ShouldStop(33554432);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.motorolasolutions.emdk.datawedge.label_type"))));
 BA.debugLineNum = 27;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
Debug.ShouldStop(67108864);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.motorolasolutions.emdk.datawedge.decode_data"))));
 BA.debugLineNum = 28;BA.debugLine="structure.Add(\"com.symbol.datawedge.api\")";
Debug.ShouldStop(134217728);
scanner._structure.runVoidMethod ("Add",(Object)((RemoteObject.createImmutable("com.symbol.datawedge.api"))));
 BA.debugLineNum = 29;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_destroy() throws Exception{
try {
		Debug.PushSubsStack("Service_Destroy (scanner) ","scanner",2,scanner.processBA,scanner.mostCurrent,41);
if (RapidSub.canDelegate("service_destroy")) { return zebra.companion.scanner.scanner.remoteMe.runUserSub(false, "scanner","service_destroy");}
 BA.debugLineNum = 41;BA.debugLine="Sub Service_Destroy";
Debug.ShouldStop(256);
 BA.debugLineNum = 43;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _service_start(RemoteObject _startingintent) throws Exception{
try {
		Debug.PushSubsStack("Service_Start (scanner) ","scanner",2,scanner.processBA,scanner.mostCurrent,31);
if (RapidSub.canDelegate("service_start")) { return zebra.companion.scanner.scanner.remoteMe.runUserSub(false, "scanner","service_start", _startingintent);}
Debug.locals.put("StartingIntent", _startingintent);
 BA.debugLineNum = 31;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 33;BA.debugLine="BC.addCategory(\"intent_category\")";
Debug.ShouldStop(1);
scanner._bc.runVoidMethod ("addCategory",(Object)(RemoteObject.createImmutable("intent_category")));
 BA.debugLineNum = 34;BA.debugLine="BC.addAction(\"scanner_intent\")";
Debug.ShouldStop(2);
scanner._bc.runVoidMethod ("addAction",(Object)(RemoteObject.createImmutable("scanner_intent")));
 BA.debugLineNum = 35;BA.debugLine="BC.SetPriority(20000000)";
Debug.ShouldStop(4);
scanner._bc.runVoidMethod ("SetPriority",(Object)(BA.numberCast(int.class, 20000000)));
 BA.debugLineNum = 36;BA.debugLine="BC.registerReceiver(\"scanner_intent\")";
Debug.ShouldStop(8);
scanner._bc.runVoidMethod ("registerReceiver",(Object)(RemoteObject.createImmutable("scanner_intent")));
 BA.debugLineNum = 37;BA.debugLine="Log(\"Scanner Service Started: \" & StartingIntent.";
Debug.ShouldStop(16);
scanner.mostCurrent.__c.runVoidMethod ("LogImpl","21572870",RemoteObject.concat(RemoteObject.createImmutable("Scanner Service Started: "),_startingintent.runMethod(true,"ExtrasToString")),0);
 BA.debugLineNum = 38;BA.debugLine="Service.StopAutomaticForeground 'Call this when t";
Debug.ShouldStop(32);
scanner.mostCurrent._service.runVoidMethod ("StopAutomaticForeground");
 BA.debugLineNum = 39;BA.debugLine="End Sub";
Debug.ShouldStop(64);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}