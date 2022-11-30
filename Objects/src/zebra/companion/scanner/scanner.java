package zebra.companion.scanner;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.objects.ServiceHelper;
import anywheresoftware.b4a.debug.*;

public class scanner extends  android.app.Service{
	public static class scanner_BR extends android.content.BroadcastReceiver {

		@Override
		public void onReceive(android.content.Context context, android.content.Intent intent) {
            BA.LogInfo("** Receiver (scanner) OnReceive **");
			android.content.Intent in = new android.content.Intent(context, scanner.class);
			if (intent != null)
				in.putExtra("b4a_internal_intent", intent);
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, BA.class);
		}

	}
    static scanner mostCurrent;
	public static BA processBA;
    private ServiceHelper _service;
    public static Class<?> getObject() {
		return scanner.class;
	}
	@Override
	public void onCreate() {
        super.onCreate();
        mostCurrent = this;
        if (processBA == null) {
		    processBA = new BA(this, null, null, "zebra.companion.scanner", "zebra.companion.scanner.scanner");
            if (BA.isShellModeRuntimeCheck(processBA)) {
                processBA.raiseEvent2(null, true, "SHELL", false);
		    }
            try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            processBA.loadHtSubs(this.getClass());
            ServiceHelper.init();
        }
        _service = new ServiceHelper(this);
        processBA.service = this;
        
        if (BA.isShellModeRuntimeCheck(processBA)) {
			processBA.raiseEvent2(null, true, "CREATE", true, "zebra.companion.scanner.scanner", processBA, _service, anywheresoftware.b4a.keywords.Common.Density);
		}
        if (!false && ServiceHelper.StarterHelper.startFromServiceCreate(processBA, false) == false) {
				
		}
		else {
            processBA.setActivityPaused(false);
            BA.LogInfo("*** Service (scanner) Create ***");
            processBA.raiseEvent(null, "service_create");
        }
        processBA.runHook("oncreate", this, null);
        if (false) {
			ServiceHelper.StarterHelper.runWaitForLayouts();
		}
    }
		@Override
	public void onStart(android.content.Intent intent, int startId) {
		onStartCommand(intent, 0, 0);
    }
    @Override
    public int onStartCommand(final android.content.Intent intent, int flags, int startId) {
    	if (ServiceHelper.StarterHelper.onStartCommand(processBA, new Runnable() {
            public void run() {
                handleStart(intent);
            }}))
			;
		else {
			ServiceHelper.StarterHelper.addWaitForLayout (new Runnable() {
				public void run() {
                    processBA.setActivityPaused(false);
                    BA.LogInfo("** Service (scanner) Create **");
                    processBA.raiseEvent(null, "service_create");
					handleStart(intent);
                    ServiceHelper.StarterHelper.removeWaitForLayout();
				}
			});
		}
        processBA.runHook("onstartcommand", this, new Object[] {intent, flags, startId});
		return android.app.Service.START_NOT_STICKY;
    }
    public void onTaskRemoved(android.content.Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (false)
            processBA.raiseEvent(null, "service_taskremoved");
            
    }
    private void handleStart(android.content.Intent intent) {
    	BA.LogInfo("** Service (scanner) Start **");
    	java.lang.reflect.Method startEvent = processBA.htSubs.get("service_start");
    	if (startEvent != null) {
    		if (startEvent.getParameterTypes().length > 0) {
    			anywheresoftware.b4a.objects.IntentWrapper iw = ServiceHelper.StarterHelper.handleStartIntent(intent, _service, processBA);
    			processBA.raiseEvent(null, "service_start", iw);
    		}
    		else {
    			processBA.raiseEvent(null, "service_start");
    		}
    	}
    }
	
	@Override
	public void onDestroy() {
        super.onDestroy();
        if (false) {
            BA.LogInfo("** Service (scanner) Destroy (ignored)**");
        }
        else {
            BA.LogInfo("** Service (scanner) Destroy **");
		    processBA.raiseEvent(null, "service_destroy");
            processBA.service = null;
		    mostCurrent = null;
		    processBA.setActivityPaused(true);
            processBA.runHook("ondestroy", this, null);
        }
	}

@Override
	public android.os.IBinder onBind(android.content.Intent intent) {
		return null;
	}public anywheresoftware.b4a.keywords.Common __c = null;
public static com.rootsoft.broadcastreceiver.BroadCastReceiver _v5 = null;
public static anywheresoftware.b4a.objects.collections.List _v6 = null;
public static anywheresoftware.b4a.objects.collections.Map _v7 = null;
public zebra.companion.scanner.main _vv4 = null;
public zebra.companion.scanner.starter _vv5 = null;
public static String  _bc_onreceive(String _action,Object _o) throws Exception{
anywheresoftware.b4a.objects.IntentWrapper _intent = null;
String _l = "";
String _strsym = "";
String _strall = "";
 //BA.debugLineNum = 46;BA.debugLine="Sub BC_OnReceive (action As String, o As Object)";
 //BA.debugLineNum = 48;BA.debugLine="Dim intent As Intent = o";
_intent = new anywheresoftware.b4a.objects.IntentWrapper();
_intent = (anywheresoftware.b4a.objects.IntentWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.IntentWrapper(), (android.content.Intent)(_o));
 //BA.debugLineNum = 49;BA.debugLine="data.Initialize()";
_v7.Initialize();
 //BA.debugLineNum = 50;BA.debugLine="For Each l As String In structure";
{
final anywheresoftware.b4a.BA.IterableList group3 = _v6;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_l = BA.ObjectToString(group3.Get(index3));
 //BA.debugLineNum = 51;BA.debugLine="If intent.HasExtra(l) Then";
if (_intent.HasExtra(_l)) { 
 //BA.debugLineNum = 52;BA.debugLine="data.Put(l.SubString(l.LastIndexOf(\".\")+1), int";
_v7.Put((Object)(_l.substring((int) (_l.lastIndexOf(".")+1))),_intent.GetExtra(_l));
 };
 }
};
 //BA.debugLineNum = 55;BA.debugLine="Dim strSym As String = data.Get(\"label_type\")";
_strsym = BA.ObjectToString(_v7.Get((Object)("label_type")));
 //BA.debugLineNum = 56;BA.debugLine="strSym = strSym.ToUpperCase";
_strsym = _strsym.toUpperCase();
 //BA.debugLineNum = 57;BA.debugLine="strSym = strSym.Replace(\"LABEL-TYPE-\",\"\")";
_strsym = _strsym.replace("LABEL-TYPE-","");
 //BA.debugLineNum = 58;BA.debugLine="DateTime.DateFormat=\"yyyy-MM-dd HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss");
 //BA.debugLineNum = 59;BA.debugLine="Dim strAll As String = Chr(34) & DateTime.Date(Da";
_strall = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+_strsym+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+BA.ObjectToString(_v7.Get((Object)("data_string")))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)));
 //BA.debugLineNum = 61;BA.debugLine="CallSubDelayed2(Main, \"ProcessScan\", strAll)";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._vv4.getObject()),"ProcessScan",(Object)(_strall));
 //BA.debugLineNum = 62;BA.debugLine="End Sub";
return "";
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim BC As BroadCastReceiver";
_v5 = new com.rootsoft.broadcastreceiver.BroadCastReceiver();
 //BA.debugLineNum = 10;BA.debugLine="Dim structure As List";
_v6 = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 11;BA.debugLine="Dim data As Map";
_v7 = new anywheresoftware.b4a.objects.collections.Map();
 //BA.debugLineNum = 12;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Service_Create";
 //BA.debugLineNum = 16;BA.debugLine="BC.Initialize(\"BC\")";
_v5.Initialize(processBA,"BC");
 //BA.debugLineNum = 17;BA.debugLine="structure.Initialize()";
_v6.Initialize();
 //BA.debugLineNum = 18;BA.debugLine="structure.Add(\"com.symbol.datawedge.source\")";
_v6.Add((Object)("com.symbol.datawedge.source"));
 //BA.debugLineNum = 19;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_string\")";
_v6.Add((Object)("com.symbol.datawedge.data_string"));
 //BA.debugLineNum = 20;BA.debugLine="structure.Add(\"com.symbol.datawedge.label_type\")";
_v6.Add((Object)("com.symbol.datawedge.label_type"));
 //BA.debugLineNum = 21;BA.debugLine="structure.Add(\"com.symbol.datawedge.decode_data\")";
_v6.Add((Object)("com.symbol.datawedge.decode_data"));
 //BA.debugLineNum = 22;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_dispatch";
_v6.Add((Object)("com.symbol.datawedge.data_dispatch_time"));
 //BA.debugLineNum = 23;BA.debugLine="structure.Add(\"com.symbol.datawedge.decoded_mode\"";
_v6.Add((Object)("com.symbol.datawedge.decoded_mode"));
 //BA.debugLineNum = 24;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_v6.Add((Object)("com.motorolasolutions.emdk.datawedge.source"));
 //BA.debugLineNum = 25;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_v6.Add((Object)("com.motorolasolutions.emdk.datawedge.data_string"));
 //BA.debugLineNum = 26;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_v6.Add((Object)("com.motorolasolutions.emdk.datawedge.label_type"));
 //BA.debugLineNum = 27;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_v6.Add((Object)("com.motorolasolutions.emdk.datawedge.decode_data"));
 //BA.debugLineNum = 28;BA.debugLine="structure.Add(\"com.symbol.datawedge.api\")";
_v6.Add((Object)("com.symbol.datawedge.api"));
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
 //BA.debugLineNum = 41;BA.debugLine="Sub Service_Destroy";
 //BA.debugLineNum = 43;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
 //BA.debugLineNum = 31;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
 //BA.debugLineNum = 33;BA.debugLine="BC.addCategory(\"intent_category\")";
_v5.addCategory("intent_category");
 //BA.debugLineNum = 34;BA.debugLine="BC.addAction(\"scanner_intent\")";
_v5.addAction("scanner_intent");
 //BA.debugLineNum = 35;BA.debugLine="BC.SetPriority(20000000)";
_v5.SetPriority((int) (20000000));
 //BA.debugLineNum = 36;BA.debugLine="BC.registerReceiver(\"scanner_intent\")";
_v5.registerReceiver("scanner_intent");
 //BA.debugLineNum = 37;BA.debugLine="Log(\"Scanner Service Started: \" & StartingIntent.";
anywheresoftware.b4a.keywords.Common.LogImpl("11507334","Scanner Service Started: "+_startingintent.ExtrasToString(),0);
 //BA.debugLineNum = 38;BA.debugLine="Service.StopAutomaticForeground 'Call this when t";
mostCurrent._service.StopAutomaticForeground();
 //BA.debugLineNum = 39;BA.debugLine="End Sub";
return "";
}
}
