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
            ServiceHelper.StarterHelper.startServiceFromReceiver (context, in, false, anywheresoftware.b4a.ShellBA.class);
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
		    processBA = new anywheresoftware.b4a.ShellBA(this, null, null, "zebra.companion.scanner", "zebra.companion.scanner.scanner");
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
	}
public anywheresoftware.b4a.keywords.Common __c = null;
public static com.rootsoft.broadcastreceiver.BroadCastReceiver _bc = null;
public static anywheresoftware.b4a.objects.collections.List _structure = null;
public static anywheresoftware.b4a.objects.collections.Map _data = null;
public zebra.companion.scanner.main _main = null;
public zebra.companion.scanner.starter _starter = null;
public static String  _bc_onreceive(String _action,Object _o) throws Exception{
RDebugUtils.currentModule="scanner";
if (Debug.shouldDelegate(processBA, "bc_onreceive", false))
	 {return ((String) Debug.delegate(processBA, "bc_onreceive", new Object[] {_action,_o}));}
anywheresoftware.b4a.objects.IntentWrapper _intent = null;
String _l = "";
String _strsym = "";
String _strall = "";
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Sub BC_OnReceive (action As String, o As Object)";
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="Dim intent As Intent = o";
_intent = new anywheresoftware.b4a.objects.IntentWrapper();
_intent = (anywheresoftware.b4a.objects.IntentWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.IntentWrapper(), (android.content.Intent)(_o));
RDebugUtils.currentLine=1703939;
 //BA.debugLineNum = 1703939;BA.debugLine="data.Initialize()";
_data.Initialize();
RDebugUtils.currentLine=1703940;
 //BA.debugLineNum = 1703940;BA.debugLine="For Each l As String In structure";
{
final anywheresoftware.b4a.BA.IterableList group3 = _structure;
final int groupLen3 = group3.getSize()
;int index3 = 0;
;
for (; index3 < groupLen3;index3++){
_l = BA.ObjectToString(group3.Get(index3));
RDebugUtils.currentLine=1703941;
 //BA.debugLineNum = 1703941;BA.debugLine="If intent.HasExtra(l) Then";
if (_intent.HasExtra(_l)) { 
RDebugUtils.currentLine=1703942;
 //BA.debugLineNum = 1703942;BA.debugLine="data.Put(l.SubString(l.LastIndexOf(\".\")+1), int";
_data.Put((Object)(_l.substring((int) (_l.lastIndexOf(".")+1))),_intent.GetExtra(_l));
 };
 }
};
RDebugUtils.currentLine=1703945;
 //BA.debugLineNum = 1703945;BA.debugLine="Dim strSym As String = data.Get(\"label_type\")";
_strsym = BA.ObjectToString(_data.Get((Object)("label_type")));
RDebugUtils.currentLine=1703946;
 //BA.debugLineNum = 1703946;BA.debugLine="strSym = strSym.ToUpperCase";
_strsym = _strsym.toUpperCase();
RDebugUtils.currentLine=1703947;
 //BA.debugLineNum = 1703947;BA.debugLine="strSym = strSym.Replace(\"LABEL-TYPE-\",\"\")";
_strsym = _strsym.replace("LABEL-TYPE-","");
RDebugUtils.currentLine=1703948;
 //BA.debugLineNum = 1703948;BA.debugLine="DateTime.DateFormat=\"yyyy-MM-dd HH:mm:ss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyyy-MM-dd HH:mm:ss");
RDebugUtils.currentLine=1703949;
 //BA.debugLineNum = 1703949;BA.debugLine="Dim strAll As String = Chr(34) & DateTime.Date(Da";
_strall = BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+_strsym+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+BA.ObjectToString(_data.Get((Object)("data_string")))+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)));
RDebugUtils.currentLine=1703951;
 //BA.debugLineNum = 1703951;BA.debugLine="CallSubDelayed2(Main, \"ProcessScan\", strAll)";
anywheresoftware.b4a.keywords.Common.CallSubDelayed2(processBA,(Object)(mostCurrent._main.getObject()),"ProcessScan",(Object)(_strall));
RDebugUtils.currentLine=1703952;
 //BA.debugLineNum = 1703952;BA.debugLine="End Sub";
return "";
}
public static String  _service_create() throws Exception{
RDebugUtils.currentModule="scanner";
if (Debug.shouldDelegate(processBA, "service_create", false))
	 {return ((String) Debug.delegate(processBA, "service_create", null));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub Service_Create";
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="BC.Initialize(\"BC\")";
_bc.Initialize(processBA,"BC");
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="structure.Initialize()";
_structure.Initialize();
RDebugUtils.currentLine=1507332;
 //BA.debugLineNum = 1507332;BA.debugLine="structure.Add(\"com.symbol.datawedge.source\")";
_structure.Add((Object)("com.symbol.datawedge.source"));
RDebugUtils.currentLine=1507333;
 //BA.debugLineNum = 1507333;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_string\")";
_structure.Add((Object)("com.symbol.datawedge.data_string"));
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="structure.Add(\"com.symbol.datawedge.label_type\")";
_structure.Add((Object)("com.symbol.datawedge.label_type"));
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="structure.Add(\"com.symbol.datawedge.decode_data\")";
_structure.Add((Object)("com.symbol.datawedge.decode_data"));
RDebugUtils.currentLine=1507336;
 //BA.debugLineNum = 1507336;BA.debugLine="structure.Add(\"com.symbol.datawedge.data_dispatch";
_structure.Add((Object)("com.symbol.datawedge.data_dispatch_time"));
RDebugUtils.currentLine=1507337;
 //BA.debugLineNum = 1507337;BA.debugLine="structure.Add(\"com.symbol.datawedge.decoded_mode\"";
_structure.Add((Object)("com.symbol.datawedge.decoded_mode"));
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_structure.Add((Object)("com.motorolasolutions.emdk.datawedge.source"));
RDebugUtils.currentLine=1507339;
 //BA.debugLineNum = 1507339;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_structure.Add((Object)("com.motorolasolutions.emdk.datawedge.data_string"));
RDebugUtils.currentLine=1507340;
 //BA.debugLineNum = 1507340;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_structure.Add((Object)("com.motorolasolutions.emdk.datawedge.label_type"));
RDebugUtils.currentLine=1507341;
 //BA.debugLineNum = 1507341;BA.debugLine="structure.Add(\"com.motorolasolutions.emdk.datawed";
_structure.Add((Object)("com.motorolasolutions.emdk.datawedge.decode_data"));
RDebugUtils.currentLine=1507342;
 //BA.debugLineNum = 1507342;BA.debugLine="structure.Add(\"com.symbol.datawedge.api\")";
_structure.Add((Object)("com.symbol.datawedge.api"));
RDebugUtils.currentLine=1507343;
 //BA.debugLineNum = 1507343;BA.debugLine="End Sub";
return "";
}
public static String  _service_destroy() throws Exception{
RDebugUtils.currentModule="scanner";
if (Debug.shouldDelegate(processBA, "service_destroy", false))
	 {return ((String) Debug.delegate(processBA, "service_destroy", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Sub Service_Destroy";
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="End Sub";
return "";
}
public static String  _service_start(anywheresoftware.b4a.objects.IntentWrapper _startingintent) throws Exception{
RDebugUtils.currentModule="scanner";
if (Debug.shouldDelegate(processBA, "service_start", false))
	 {return ((String) Debug.delegate(processBA, "service_start", new Object[] {_startingintent}));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub Service_Start (StartingIntent As Intent)";
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="BC.addCategory(\"intent_category\")";
_bc.addCategory("intent_category");
RDebugUtils.currentLine=1572867;
 //BA.debugLineNum = 1572867;BA.debugLine="BC.addAction(\"scanner_intent\")";
_bc.addAction("scanner_intent");
RDebugUtils.currentLine=1572868;
 //BA.debugLineNum = 1572868;BA.debugLine="BC.SetPriority(20000000)";
_bc.SetPriority((int) (20000000));
RDebugUtils.currentLine=1572869;
 //BA.debugLineNum = 1572869;BA.debugLine="BC.registerReceiver(\"scanner_intent\")";
_bc.registerReceiver("scanner_intent");
RDebugUtils.currentLine=1572870;
 //BA.debugLineNum = 1572870;BA.debugLine="Log(\"Scanner Service Started: \" & StartingIntent.";
anywheresoftware.b4a.keywords.Common.LogImpl("21572870","Scanner Service Started: "+_startingintent.ExtrasToString(),0);
RDebugUtils.currentLine=1572871;
 //BA.debugLineNum = 1572871;BA.debugLine="Service.StopAutomaticForeground 'Call this when t";
mostCurrent._service.StopAutomaticForeground();
RDebugUtils.currentLine=1572872;
 //BA.debugLineNum = 1572872;BA.debugLine="End Sub";
return "";
}
}