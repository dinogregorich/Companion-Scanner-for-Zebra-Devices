package zebra.companion.scanner;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = true;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "zebra.companion.scanner", "zebra.companion.scanner.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "zebra.companion.scanner", "zebra.companion.scanner.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "zebra.companion.scanner.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create, isFirst = " + isFirst + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}
public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}

private static BA killProgramHelper(BA ba) {
    if (ba == null)
        return null;
    anywheresoftware.b4a.BA.SharedProcessBA sharedProcessBA = ba.sharedProcessBA;
    if (sharedProcessBA == null || sharedProcessBA.activityBA == null)
        return null;
    return sharedProcessBA.activityBA.get();
}
public static void killProgram() {
     {
            Activity __a = null;
            if (main.previousOne != null) {
				__a = main.previousOne.get();
			}
            else {
                BA ba = killProgramHelper(main.mostCurrent == null ? null : main.mostCurrent.processBA);
                if (ba != null) __a = ba.activity;
            }
            if (__a != null)
				__a.finish();}

BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, starter.class));
BA.applicationContext.stopService(new android.content.Intent(BA.applicationContext, scanner.class));
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Timer _t = null;
public batteryviewwrapper.batteryviewWrapper _bv1 = null;
public zebra.companion.scanner.batteryutilities _bu = null;
public static int[] _batterystatus = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_scanresult = null;
public zebra.companion.scanner.starter _starter = null;
public zebra.companion.scanner.scanner _scanner = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
RDebugUtils.currentLine=131072;
 //BA.debugLineNum = 131072;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=131073;
 //BA.debugLineNum = 131073;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
RDebugUtils.currentLine=131074;
 //BA.debugLineNum = 131074;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
RDebugUtils.currentLine=131075;
 //BA.debugLineNum = 131075;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
RDebugUtils.currentLine=131076;
 //BA.debugLineNum = 131076;BA.debugLine="r.Target = r.RunMethod(\"getDisplayMetrics\")";
_r.Target = _r.RunMethod("getDisplayMetrics");
RDebugUtils.currentLine=131092;
 //BA.debugLineNum = 131092;BA.debugLine="Activity.Height = 110%y";
mostCurrent._activity.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (110),mostCurrent.activityBA));
RDebugUtils.currentLine=131094;
 //BA.debugLineNum = 131094;BA.debugLine="If GetDeviceLayoutValues.Width/r.GetField(\"xdpi\")";
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).Width/(double)(double)(BA.ObjectToNumber(_r.GetField("xdpi")))<2) { 
RDebugUtils.currentLine=131095;
 //BA.debugLineNum = 131095;BA.debugLine="Activity.LoadLayout(\"main_small\")";
mostCurrent._activity.LoadLayout("main_small",mostCurrent.activityBA);
 }else {
RDebugUtils.currentLine=131097;
 //BA.debugLineNum = 131097;BA.debugLine="Activity.LoadLayout(\"main_normal\")";
mostCurrent._activity.LoadLayout("main_normal",mostCurrent.activityBA);
 };
RDebugUtils.currentLine=131100;
 //BA.debugLineNum = 131100;BA.debugLine="bu.Initialize";
mostCurrent._bu._initialize /*String*/ (null,processBA);
RDebugUtils.currentLine=131101;
 //BA.debugLineNum = 131101;BA.debugLine="batterystatus = bu.BatteryInformation";
_batterystatus = mostCurrent._bu._getbatteryinformation /*int[]*/ (null);
RDebugUtils.currentLine=131102;
 //BA.debugLineNum = 131102;BA.debugLine="bv1.Level = batterystatus(0)";
mostCurrent._bv1.setLevel(_batterystatus[(int) (0)]);
RDebugUtils.currentLine=131103;
 //BA.debugLineNum = 131103;BA.debugLine="If batterystatus(8) = 1 Then";
if (_batterystatus[(int) (8)]==1) { 
RDebugUtils.currentLine=131104;
 //BA.debugLineNum = 131104;BA.debugLine="bv1.Charging = True";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=131106;
 //BA.debugLineNum = 131106;BA.debugLine="bv1.Charging = False";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=131108;
 //BA.debugLineNum = 131108;BA.debugLine="t.Initialize(\"t\", 20000)";
_t.Initialize(processBA,"t",(long) (20000));
RDebugUtils.currentLine=131109;
 //BA.debugLineNum = 131109;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="main";
RDebugUtils.currentLine=262144;
 //BA.debugLineNum = 262144;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=262145;
 //BA.debugLineNum = 262145;BA.debugLine="t.Enabled = False";
_t.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=262146;
 //BA.debugLineNum = 262146;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
anywheresoftware.b4j.object.JavaObject _jo = null;
String _strmsg = "";
RDebugUtils.currentLine=196608;
 //BA.debugLineNum = 196608;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=196609;
 //BA.debugLineNum = 196609;BA.debugLine="Try";
try {RDebugUtils.currentLine=196610;
 //BA.debugLineNum = 196610;BA.debugLine="Dim jo As JavaObject = Activity";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._activity.getObject()));
RDebugUtils.currentLine=196611;
 //BA.debugLineNum = 196611;BA.debugLine="jo.RunMethod(\"setSystemUiVisibility\", Array As O";
_jo.RunMethod("setSystemUiVisibility",new Object[]{(Object)(5894)});
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); };
RDebugUtils.currentLine=196615;
 //BA.debugLineNum = 196615;BA.debugLine="Dim strMsg As String = \"Press Scan Button(s) To B";
_strmsg = "Press Scan Button(s) To Begin."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"When finished, tap 'Save' to save the captured scans to a directory.";
RDebugUtils.currentLine=196616;
 //BA.debugLineNum = 196616;BA.debugLine="strMsg = strMsg & CRLF & CRLF & \"A confirmation m";
_strmsg = _strmsg+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"A confirmation message will appear when successfully saved.";
RDebugUtils.currentLine=196617;
 //BA.debugLineNum = 196617;BA.debugLine="label_scanResult.Text = strMsg";
mostCurrent._label_scanresult.setText(BA.ObjectToCharSequence(_strmsg));
RDebugUtils.currentLine=196618;
 //BA.debugLineNum = 196618;BA.debugLine="label_scanResult.TextColor = Colors.ARGB(255, 22,";
mostCurrent._label_scanresult.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (22),(int) (22),(int) (22)));
RDebugUtils.currentLine=196619;
 //BA.debugLineNum = 196619;BA.debugLine="t.Enabled = True";
_t.setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=196620;
 //BA.debugLineNum = 196620;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Tr";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp")==anywheresoftware.b4a.keywords.Common.True) { 
RDebugUtils.currentLine=196621;
 //BA.debugLineNum = 196621;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
_findviewbytag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
RDebugUtils.currentLine=196623;
 //BA.debugLineNum = 196623;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.objects.ConcreteViewWrapper  _findviewbytag(anywheresoftware.b4a.objects.PanelWrapper _parent,String _name) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "findviewbytag", false))
	 {return ((anywheresoftware.b4a.objects.ConcreteViewWrapper) Debug.delegate(mostCurrent.activityBA, "findviewbytag", new Object[] {_parent,_name}));}
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
RDebugUtils.currentLine=983040;
 //BA.debugLineNum = 983040;BA.debugLine="Sub FindViewByTag(Parent As Panel, Name As String)";
RDebugUtils.currentLine=983041;
 //BA.debugLineNum = 983041;BA.debugLine="For Each v As View In Parent.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group1 = _parent.GetAllViewsRecursive();
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group1.Get(index1)));
RDebugUtils.currentLine=983042;
 //BA.debugLineNum = 983042;BA.debugLine="If Name = v.Tag Then Return v";
if ((_name).equals(BA.ObjectToString(_v.getTag()))) { 
if (true) return _v;};
 }
};
RDebugUtils.currentLine=983044;
 //BA.debugLineNum = 983044;BA.debugLine="Log(\"View not found: \" & Name)";
anywheresoftware.b4a.keywords.Common.LogImpl("2983044","View not found: "+_name,0);
RDebugUtils.currentLine=983045;
 //BA.debugLineNum = 983045;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=983046;
 //BA.debugLineNum = 983046;BA.debugLine="End Sub";
return null;
}
public static void  _buttonsave_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "buttonsave_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "buttonsave_click", null); return;}
ResumableSub_ButtonSave_Click rsub = new ResumableSub_ButtonSave_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ButtonSave_Click extends BA.ResumableSub {
public ResumableSub_ButtonSave_Click(zebra.companion.scanner.main parent) {
this.parent = parent;
}
zebra.companion.scanner.main parent;
String _strfilename = "";
boolean _success = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=393217;
 //BA.debugLineNum = 393217;BA.debugLine="DateTime.DateFormat=\"yyMMdd-HHmmss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyMMdd-HHmmss");
RDebugUtils.currentLine=393218;
 //BA.debugLineNum = 393218;BA.debugLine="Dim strFileName As String = \"scans_\" & DateTime.D";
_strfilename = "scans_"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+".csv";
RDebugUtils.currentLine=393219;
 //BA.debugLineNum = 393219;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirInternal,";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "buttonsave_click"), _saveas(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp"),"application/octet-stream",_strfilename));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
;
RDebugUtils.currentLine=393220;
 //BA.debugLineNum = 393220;BA.debugLine="Log(\"File saved successfully? \" & Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("2393220","File saved successfully? "+BA.ObjectToString(_success),0);
RDebugUtils.currentLine=393221;
 //BA.debugLineNum = 393221;BA.debugLine="If Success = True Then";
if (true) break;

case 1:
//if
this.state = 6;
if (_success==anywheresoftware.b4a.keywords.Common.True) { 
this.state = 3;
}else {
this.state = 5;
}if (true) break;

case 3:
//C
this.state = 6;
RDebugUtils.currentLine=393222;
 //BA.debugLineNum = 393222;BA.debugLine="File.Delete(File.DirInternal,\"SCANS.tmp\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp");
RDebugUtils.currentLine=393223;
 //BA.debugLineNum = 393223;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = Fals";
_findviewbytag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(parent.mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 5:
//C
this.state = 6;
RDebugUtils.currentLine=393225;
 //BA.debugLineNum = 393225;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File did not save.  Try again"),BA.ObjectToCharSequence("Write File Error"),processBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
RDebugUtils.currentLine=393227;
 //BA.debugLineNum = 393227;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _saveas(anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _source,String _mimetype,String _title) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "saveas", false))
	 {return ((anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) Debug.delegate(mostCurrent.activityBA, "saveas", new Object[] {_source,_mimetype,_title}));}
ResumableSub_SaveAs rsub = new ResumableSub_SaveAs(null,_source,_mimetype,_title);
rsub.resume(processBA, null);
return (anywheresoftware.b4a.keywords.Common.ResumableSubWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.Common.ResumableSubWrapper(), rsub);
}
public static class ResumableSub_SaveAs extends BA.ResumableSub {
public ResumableSub_SaveAs(zebra.companion.scanner.main parent,anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _source,String _mimetype,String _title) {
this.parent = parent;
this._source = _source;
this._mimetype = _mimetype;
this._title = _title;
}
zebra.companion.scanner.main parent;
anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _source;
String _mimetype;
String _title;
anywheresoftware.b4a.objects.IntentWrapper _intent = null;
String _methodname = "";
Object[] _args = null;
anywheresoftware.b4a.objects.IntentWrapper _result = null;
anywheresoftware.b4j.object.JavaObject _jo = null;
anywheresoftware.b4j.object.JavaObject _ctxt = null;
anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper _out = null;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
RDebugUtils.currentLine=458753;
 //BA.debugLineNum = 458753;BA.debugLine="Dim intent As Intent";
_intent = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=458754;
 //BA.debugLineNum = 458754;BA.debugLine="intent.Initialize(\"android.intent.action.CREATE_D";
_intent.Initialize("android.intent.action.CREATE_DOCUMENT","");
RDebugUtils.currentLine=458755;
 //BA.debugLineNum = 458755;BA.debugLine="intent.AddCategory(\"android.intent.category.OPENA";
_intent.AddCategory("android.intent.category.OPENABLE");
RDebugUtils.currentLine=458756;
 //BA.debugLineNum = 458756;BA.debugLine="intent.PutExtra(\"android.intent.extra.TITLE\", Tit";
_intent.PutExtra("android.intent.extra.TITLE",(Object)(_title));
RDebugUtils.currentLine=458757;
 //BA.debugLineNum = 458757;BA.debugLine="intent.SetType(MimeType)";
_intent.SetType(_mimetype);
RDebugUtils.currentLine=458758;
 //BA.debugLineNum = 458758;BA.debugLine="StartActivityForResult(intent)";
_startactivityforresult(_intent);
RDebugUtils.currentLine=458759;
 //BA.debugLineNum = 458759;BA.debugLine="Wait For ion_Event (MethodName As String, Args()";
anywheresoftware.b4a.keywords.Common.WaitFor("ion_event", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "saveas"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_methodname = (String) result[0];
_args = (Object[]) result[1];
;
RDebugUtils.currentLine=458760;
 //BA.debugLineNum = 458760;BA.debugLine="If -1 = Args(0) Then";
if (true) break;

case 1:
//if
this.state = 4;
if (-1==(double)(BA.ObjectToNumber(_args[(int) (0)]))) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=458762;
 //BA.debugLineNum = 458762;BA.debugLine="Dim result As Intent = Args(1)";
_result = new anywheresoftware.b4a.objects.IntentWrapper();
_result = (anywheresoftware.b4a.objects.IntentWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.IntentWrapper(), (android.content.Intent)(_args[(int) (1)]));
RDebugUtils.currentLine=458763;
 //BA.debugLineNum = 458763;BA.debugLine="Dim jo As JavaObject = result";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_result.getObject()));
RDebugUtils.currentLine=458764;
 //BA.debugLineNum = 458764;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=458765;
 //BA.debugLineNum = 458765;BA.debugLine="Dim out As OutputStream = ctxt.InitializeContext";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_ctxt.InitializeContext(processBA).RunMethodJO("getContentResolver",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("openOutputStream",new Object[]{_jo.RunMethod("getData",(Object[])(anywheresoftware.b4a.keywords.Common.Null))})));
RDebugUtils.currentLine=458766;
 //BA.debugLineNum = 458766;BA.debugLine="File.Copy2(Source, out)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_source.getObject()),(java.io.OutputStream)(_out.getObject()));
RDebugUtils.currentLine=458767;
 //BA.debugLineNum = 458767;BA.debugLine="out.Close";
_out.Close();
RDebugUtils.currentLine=458768;
 //BA.debugLineNum = 458768;BA.debugLine="MsgboxAsync(\"File \" & Title & \" was saved succes";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File "+_title+" was saved successfully"),BA.ObjectToCharSequence("Write File Success"),processBA);
RDebugUtils.currentLine=458769;
 //BA.debugLineNum = 458769;BA.debugLine="Return True";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
 if (true) break;

case 4:
//C
this.state = -1;
;
RDebugUtils.currentLine=458771;
 //BA.debugLineNum = 458771;BA.debugLine="Return False";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.False));return;};
RDebugUtils.currentLine=458772;
 //BA.debugLineNum = 458772;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static Object  _getba() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "getba", false))
	 {return ((Object) Debug.delegate(mostCurrent.activityBA, "getba", null));}
anywheresoftware.b4j.object.JavaObject _jo = null;
String _cls = "";
RDebugUtils.currentLine=589824;
 //BA.debugLineNum = 589824;BA.debugLine="Sub GetBA As Object";
RDebugUtils.currentLine=589825;
 //BA.debugLineNum = 589825;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=589826;
 //BA.debugLineNum = 589826;BA.debugLine="Dim cls As String = Me";
_cls = BA.ObjectToString(main.getObject());
RDebugUtils.currentLine=589827;
 //BA.debugLineNum = 589827;BA.debugLine="cls = cls.SubString(\"class \".Length)";
_cls = _cls.substring("class ".length());
RDebugUtils.currentLine=589828;
 //BA.debugLineNum = 589828;BA.debugLine="jo.InitializeStatic(cls)";
_jo.InitializeStatic(_cls);
RDebugUtils.currentLine=589829;
 //BA.debugLineNum = 589829;BA.debugLine="Return jo.GetField(\"processBA\")";
if (true) return _jo.GetField("processBA");
RDebugUtils.currentLine=589830;
 //BA.debugLineNum = 589830;BA.debugLine="End Sub";
return null;
}
public static void  _imageview1_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "imageview1_click", false))
	 {Debug.delegate(mostCurrent.activityBA, "imageview1_click", null); return;}
ResumableSub_ImageView1_Click rsub = new ResumableSub_ImageView1_Click(null);
rsub.resume(processBA, null);
}
public static class ResumableSub_ImageView1_Click extends BA.ResumableSub {
public ResumableSub_ImageView1_Click(zebra.companion.scanner.main parent) {
this.parent = parent;
}
zebra.companion.scanner.main parent;
int _result = 0;
String _strfilename = "";
boolean _success = false;

@Override
public void resume(BA ba, Object[] result) throws Exception{
RDebugUtils.currentModule="main";

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
RDebugUtils.currentLine=851969;
 //BA.debugLineNum = 851969;BA.debugLine="MsgboxAsync(\"Copy DataWedge profile to a location";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Copy DataWedge profile to a location such as the downloads directory. Then import profile into DataWedge."),BA.ObjectToCharSequence("DataWedge Profile"),processBA);
RDebugUtils.currentLine=851970;
 //BA.debugLineNum = 851970;BA.debugLine="Wait For MsgBox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "imageview1_click"), null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (Integer) result[0];
;
RDebugUtils.currentLine=851971;
 //BA.debugLineNum = 851971;BA.debugLine="Dim strFileName As String = \"dwprofile_ZebraCompa";
_strfilename = "dwprofile_ZebraCompanionScanner_DW6.db";
RDebugUtils.currentLine=851972;
 //BA.debugLineNum = 851972;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirAssets,st";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, new anywheresoftware.b4a.shell.DebugResumableSub.DelegatableResumableSub(this, "main", "imageview1_click"), _saveas(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_strfilename),"application/x-sqlite3",_strfilename));
this.state = 6;
return;
case 6:
//C
this.state = 1;
_success = (Boolean) result[0];
;
RDebugUtils.currentLine=851973;
 //BA.debugLineNum = 851973;BA.debugLine="Log(\"File saved successfully? \" & Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("2851973","File saved successfully? "+BA.ObjectToString(_success),0);
RDebugUtils.currentLine=851974;
 //BA.debugLineNum = 851974;BA.debugLine="If Success = False Then";
if (true) break;

case 1:
//if
this.state = 4;
if (_success==anywheresoftware.b4a.keywords.Common.False) { 
this.state = 3;
}if (true) break;

case 3:
//C
this.state = 4;
RDebugUtils.currentLine=851975;
 //BA.debugLineNum = 851975;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File did not save.  Try again"),BA.ObjectToCharSequence("Write File Error"),processBA);
 if (true) break;

case 4:
//C
this.state = -1;
;
RDebugUtils.currentLine=851977;
 //BA.debugLineNum = 851977;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static String  _processscan(String _s) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "processscan", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "processscan", new Object[] {_s}));}
anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _writer = null;
RDebugUtils.currentLine=327680;
 //BA.debugLineNum = 327680;BA.debugLine="Sub ProcessScan (s As String)";
RDebugUtils.currentLine=327681;
 //BA.debugLineNum = 327681;BA.debugLine="Log(s)";
anywheresoftware.b4a.keywords.Common.LogImpl("2327681",_s,0);
RDebugUtils.currentLine=327682;
 //BA.debugLineNum = 327682;BA.debugLine="label_scanResult.Text = s.Replace(\",\",CRLF)";
mostCurrent._label_scanresult.setText(BA.ObjectToCharSequence(_s.replace(",",anywheresoftware.b4a.keywords.Common.CRLF)));
RDebugUtils.currentLine=327683;
 //BA.debugLineNum = 327683;BA.debugLine="Dim Writer As TextWriter";
_writer = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
RDebugUtils.currentLine=327684;
 //BA.debugLineNum = 327684;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Fa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp")==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=327685;
 //BA.debugLineNum = 327685;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
_writer.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp",anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=327686;
 //BA.debugLineNum = 327686;BA.debugLine="Writer.WriteLine(Chr(34) & \"Date/Time\" & Chr(34)";
_writer.WriteLine(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Date/Time"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Symbology"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Value"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34))));
RDebugUtils.currentLine=327687;
 //BA.debugLineNum = 327687;BA.debugLine="Writer.WriteLine(s)";
_writer.WriteLine(_s);
RDebugUtils.currentLine=327688;
 //BA.debugLineNum = 327688;BA.debugLine="Writer.Close";
_writer.Close();
 }else {
RDebugUtils.currentLine=327690;
 //BA.debugLineNum = 327690;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
_writer.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp",anywheresoftware.b4a.keywords.Common.True).getObject()));
RDebugUtils.currentLine=327691;
 //BA.debugLineNum = 327691;BA.debugLine="Writer.WriteLine(s)";
_writer.WriteLine(_s);
RDebugUtils.currentLine=327692;
 //BA.debugLineNum = 327692;BA.debugLine="Writer.Close";
_writer.Close();
 };
RDebugUtils.currentLine=327694;
 //BA.debugLineNum = 327694;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
_findviewbytag((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=327695;
 //BA.debugLineNum = 327695;BA.debugLine="End Sub";
return "";
}
public static String  _startactivityforresult(anywheresoftware.b4a.objects.IntentWrapper _i) throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "startactivityforresult", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "startactivityforresult", new Object[] {_i}));}
anywheresoftware.b4j.object.JavaObject _jo = null;
Object _ion = null;
RDebugUtils.currentLine=524288;
 //BA.debugLineNum = 524288;BA.debugLine="Sub StartActivityForResult(i As Intent)";
RDebugUtils.currentLine=524289;
 //BA.debugLineNum = 524289;BA.debugLine="Dim jo As JavaObject = GetBA";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_getba()));
RDebugUtils.currentLine=524290;
 //BA.debugLineNum = 524290;BA.debugLine="Dim ion As Object";
_ion = new Object();
RDebugUtils.currentLine=524291;
 //BA.debugLineNum = 524291;BA.debugLine="ion = jo.CreateEvent(\"anywheresoftware.b4a.IOnAct";
_ion = _jo.CreateEvent(processBA,"anywheresoftware.b4a.IOnActivityResult","ion",anywheresoftware.b4a.keywords.Common.Null);
RDebugUtils.currentLine=524292;
 //BA.debugLineNum = 524292;BA.debugLine="jo.RunMethod(\"startActivityForResult\", Array(ion,";
_jo.RunMethod("startActivityForResult",new Object[]{_ion,(Object)(_i.getObject())});
RDebugUtils.currentLine=524293;
 //BA.debugLineNum = 524293;BA.debugLine="End Sub";
return "";
}
public static String  _scanadd() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scanadd", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scanadd", null));}
String _softscantrigger = "";
String _extradata = "";
anywheresoftware.b4j.object.JavaObject _jobj = null;
anywheresoftware.b4a.objects.IntentWrapper _iobj = null;
RDebugUtils.currentLine=655360;
 //BA.debugLineNum = 655360;BA.debugLine="Sub ScanAdd";
RDebugUtils.currentLine=655361;
 //BA.debugLineNum = 655361;BA.debugLine="Dim softScanTrigger As String = \"com.symbol.dataw";
_softscantrigger = "com.symbol.datawedge.api.ACTION";
RDebugUtils.currentLine=655362;
 //BA.debugLineNum = 655362;BA.debugLine="Dim extraData As String = \"com.symbol.datawedge.a";
_extradata = "com.symbol.datawedge.api.SOFT_SCAN_TRIGGER";
RDebugUtils.currentLine=655363;
 //BA.debugLineNum = 655363;BA.debugLine="Dim jObj As JavaObject";
_jobj = new anywheresoftware.b4j.object.JavaObject();
RDebugUtils.currentLine=655364;
 //BA.debugLineNum = 655364;BA.debugLine="jObj.InitializeContext";
_jobj.InitializeContext(processBA);
RDebugUtils.currentLine=655365;
 //BA.debugLineNum = 655365;BA.debugLine="Dim iObj As Intent";
_iobj = new anywheresoftware.b4a.objects.IntentWrapper();
RDebugUtils.currentLine=655366;
 //BA.debugLineNum = 655366;BA.debugLine="iObj.Initialize(softScanTrigger,\"\")";
_iobj.Initialize(_softscantrigger,"");
RDebugUtils.currentLine=655367;
 //BA.debugLineNum = 655367;BA.debugLine="iObj.PutExtra(extraData, \"START_SCANNING\")";
_iobj.PutExtra(_extradata,(Object)("START_SCANNING"));
RDebugUtils.currentLine=655368;
 //BA.debugLineNum = 655368;BA.debugLine="jObj.RunMethod(\"sendBroadcast\", Array(iObj))";
_jobj.RunMethod("sendBroadcast",new Object[]{(Object)(_iobj.getObject())});
RDebugUtils.currentLine=655369;
 //BA.debugLineNum = 655369;BA.debugLine="Log(\"Broadcast Sent\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2655369","Broadcast Sent",0);
RDebugUtils.currentLine=655370;
 //BA.debugLineNum = 655370;BA.debugLine="End Sub";
return "";
}
public static String  _scanadd_click() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scanadd_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scanadd_click", null));}
RDebugUtils.currentLine=720896;
 //BA.debugLineNum = 720896;BA.debugLine="Sub ScanAdd_Click";
RDebugUtils.currentLine=720897;
 //BA.debugLineNum = 720897;BA.debugLine="Log(\"Short Click\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2720897","Short Click",0);
RDebugUtils.currentLine=720898;
 //BA.debugLineNum = 720898;BA.debugLine="CallSub(Me, ScanAdd)";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,main.getObject(),_scanadd());
RDebugUtils.currentLine=720899;
 //BA.debugLineNum = 720899;BA.debugLine="End Sub";
return "";
}
public static String  _scanadd_longclick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "scanadd_longclick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "scanadd_longclick", null));}
RDebugUtils.currentLine=786432;
 //BA.debugLineNum = 786432;BA.debugLine="Private Sub ScanAdd_LongClick";
RDebugUtils.currentLine=786433;
 //BA.debugLineNum = 786433;BA.debugLine="Log(\"Long Click\")";
anywheresoftware.b4a.keywords.Common.LogImpl("2786433","Long Click",0);
RDebugUtils.currentLine=786434;
 //BA.debugLineNum = 786434;BA.debugLine="CallSub(Me, ScanAdd)";
anywheresoftware.b4a.keywords.Common.CallSubDebug(processBA,main.getObject(),_scanadd());
RDebugUtils.currentLine=786435;
 //BA.debugLineNum = 786435;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
RDebugUtils.currentModule="main";
if (Debug.shouldDelegate(mostCurrent.activityBA, "t_tick", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "t_tick", null));}
RDebugUtils.currentLine=917504;
 //BA.debugLineNum = 917504;BA.debugLine="Sub t_tick";
RDebugUtils.currentLine=917505;
 //BA.debugLineNum = 917505;BA.debugLine="batterystatus = bu.BatteryInformation";
_batterystatus = mostCurrent._bu._getbatteryinformation /*int[]*/ (null);
RDebugUtils.currentLine=917506;
 //BA.debugLineNum = 917506;BA.debugLine="bv1.Level = batterystatus(0)";
mostCurrent._bv1.setLevel(_batterystatus[(int) (0)]);
RDebugUtils.currentLine=917507;
 //BA.debugLineNum = 917507;BA.debugLine="If batterystatus(8) = 1 Then";
if (_batterystatus[(int) (8)]==1) { 
RDebugUtils.currentLine=917508;
 //BA.debugLineNum = 917508;BA.debugLine="bv1.Charging = True";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.True);
 }else {
RDebugUtils.currentLine=917510;
 //BA.debugLineNum = 917510;BA.debugLine="bv1.Charging = False";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=917512;
 //BA.debugLineNum = 917512;BA.debugLine="End Sub";
return "";
}
}