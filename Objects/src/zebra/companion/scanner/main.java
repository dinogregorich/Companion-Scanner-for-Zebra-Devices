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
			processBA = new BA(this.getApplicationContext(), null, null, "zebra.companion.scanner", "zebra.companion.scanner.main");
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

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.Timer _v0 = null;
public batteryviewwrapper.batteryviewWrapper _bv1 = null;
public zebra.companion.scanner.batteryutilities _vv7 = null;
public static int[] _vv0 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label_scanresult = null;
public zebra.companion.scanner.starter _vv5 = null;
public zebra.companion.scanner.scanner _vv6 = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.agraham.reflection.Reflection _r = null;
 //BA.debugLineNum = 31;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 32;BA.debugLine="Dim r As Reflector";
_r = new anywheresoftware.b4a.agraham.reflection.Reflection();
 //BA.debugLineNum = 33;BA.debugLine="r.Target = r.GetContext";
_r.Target = (Object)(_r.GetContext(processBA));
 //BA.debugLineNum = 34;BA.debugLine="r.Target = r.RunMethod(\"getResources\")";
_r.Target = _r.RunMethod("getResources");
 //BA.debugLineNum = 35;BA.debugLine="r.Target = r.RunMethod(\"getDisplayMetrics\")";
_r.Target = _r.RunMethod("getDisplayMetrics");
 //BA.debugLineNum = 51;BA.debugLine="Activity.Height = 110%y";
mostCurrent._activity.setHeight(anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (110),mostCurrent.activityBA));
 //BA.debugLineNum = 53;BA.debugLine="If GetDeviceLayoutValues.Width/r.GetField(\"xdpi\")";
if (anywheresoftware.b4a.keywords.Common.GetDeviceLayoutValues(mostCurrent.activityBA).Width/(double)(double)(BA.ObjectToNumber(_r.GetField("xdpi")))<2) { 
 //BA.debugLineNum = 54;BA.debugLine="Activity.LoadLayout(\"main_small\")";
mostCurrent._activity.LoadLayout("main_small",mostCurrent.activityBA);
 }else {
 //BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"main_normal\")";
mostCurrent._activity.LoadLayout("main_normal",mostCurrent.activityBA);
 };
 //BA.debugLineNum = 59;BA.debugLine="bu.Initialize";
mostCurrent._vv7._initialize /*String*/ (processBA);
 //BA.debugLineNum = 60;BA.debugLine="batterystatus = bu.BatteryInformation";
_vv0 = mostCurrent._vv7._getvv2 /*int[]*/ ();
 //BA.debugLineNum = 61;BA.debugLine="bv1.Level = batterystatus(0)";
mostCurrent._bv1.setLevel(_vv0[(int) (0)]);
 //BA.debugLineNum = 62;BA.debugLine="If batterystatus(8) = 1 Then";
if (_vv0[(int) (8)]==1) { 
 //BA.debugLineNum = 63;BA.debugLine="bv1.Charging = True";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 65;BA.debugLine="bv1.Charging = False";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 67;BA.debugLine="t.Initialize(\"t\", 20000)";
_v0.Initialize(processBA,"t",(long) (20000));
 //BA.debugLineNum = 68;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 87;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 88;BA.debugLine="t.Enabled = False";
_v0.setEnabled(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 89;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
String _strmsg = "";
 //BA.debugLineNum = 70;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 71;BA.debugLine="Try";
try { //BA.debugLineNum = 72;BA.debugLine="Dim jo As JavaObject = Activity";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(mostCurrent._activity.getObject()));
 //BA.debugLineNum = 73;BA.debugLine="jo.RunMethod(\"setSystemUiVisibility\", Array As O";
_jo.RunMethod("setSystemUiVisibility",new Object[]{(Object)(5894)});
 } 
       catch (Exception e5) {
			processBA.setLastException(e5); };
 //BA.debugLineNum = 77;BA.debugLine="Dim strMsg As String = \"Press Scan Button(s) To B";
_strmsg = "Press Scan Button(s) To Begin."+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"When finished, tap 'Save' to save the captured scans to a directory.";
 //BA.debugLineNum = 78;BA.debugLine="strMsg = strMsg & CRLF & CRLF & \"A confirmation m";
_strmsg = _strmsg+anywheresoftware.b4a.keywords.Common.CRLF+anywheresoftware.b4a.keywords.Common.CRLF+"A confirmation message will appear when successfully saved.";
 //BA.debugLineNum = 79;BA.debugLine="label_scanResult.Text = strMsg";
mostCurrent._label_scanresult.setText(BA.ObjectToCharSequence(_strmsg));
 //BA.debugLineNum = 80;BA.debugLine="label_scanResult.TextColor = Colors.ARGB(255, 22,";
mostCurrent._label_scanresult.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.ARGB((int) (255),(int) (22),(int) (22),(int) (22)));
 //BA.debugLineNum = 81;BA.debugLine="t.Enabled = True";
_v0.setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 82;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Tr";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp")==anywheresoftware.b4a.keywords.Common.True) { 
 //BA.debugLineNum = 83;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
_vvv1((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 85;BA.debugLine="End Sub";
return "";
}
public static void  _buttonsave_click() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 109;BA.debugLine="DateTime.DateFormat=\"yyMMdd-HHmmss\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("yyMMdd-HHmmss");
 //BA.debugLineNum = 110;BA.debugLine="Dim strFileName As String = \"scans_\" & DateTime.D";
_strfilename = "scans_"+anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())+".csv";
 //BA.debugLineNum = 111;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirInternal,";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _vvv2(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp"),"application/octet-stream",_strfilename));
this.state = 7;
return;
case 7:
//C
this.state = 1;
_success = (Boolean) result[0];
;
 //BA.debugLineNum = 112;BA.debugLine="Log(\"File saved successfully? \" & Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("1393220","File saved successfully? "+BA.ObjectToString(_success),0);
 //BA.debugLineNum = 113;BA.debugLine="If Success = True Then";
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
 //BA.debugLineNum = 114;BA.debugLine="File.Delete(File.DirInternal,\"SCANS.tmp\")";
anywheresoftware.b4a.keywords.Common.File.Delete(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp");
 //BA.debugLineNum = 115;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = Fals";
_vvv1((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(parent.mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.False);
 if (true) break;

case 5:
//C
this.state = 6;
 //BA.debugLineNum = 117;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File did not save.  Try again"),BA.ObjectToCharSequence("Write File Error"),processBA);
 if (true) break;

case 6:
//C
this.state = -1;
;
 //BA.debugLineNum = 119;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _complete(boolean _success) throws Exception{
}
public static anywheresoftware.b4a.objects.ConcreteViewWrapper  _vvv1(anywheresoftware.b4a.objects.PanelWrapper _parent,String _name) throws Exception{
anywheresoftware.b4a.objects.ConcreteViewWrapper _v = null;
 //BA.debugLineNum = 201;BA.debugLine="Sub FindViewByTag(Parent As Panel, Name As String)";
 //BA.debugLineNum = 202;BA.debugLine="For Each v As View In Parent.GetAllViewsRecursive";
_v = new anywheresoftware.b4a.objects.ConcreteViewWrapper();
{
final anywheresoftware.b4a.BA.IterableList group1 = _parent.GetAllViewsRecursive();
final int groupLen1 = group1.getSize()
;int index1 = 0;
;
for (; index1 < groupLen1;index1++){
_v = (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(group1.Get(index1)));
 //BA.debugLineNum = 203;BA.debugLine="If Name = v.Tag Then Return v";
if ((_name).equals(BA.ObjectToString(_v.getTag()))) { 
if (true) return _v;};
 }
};
 //BA.debugLineNum = 205;BA.debugLine="Log(\"View not found: \" & Name)";
anywheresoftware.b4a.keywords.Common.LogImpl("1917508","View not found: "+_name,0);
 //BA.debugLineNum = 206;BA.debugLine="Return Null";
if (true) return (anywheresoftware.b4a.objects.ConcreteViewWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.ConcreteViewWrapper(), (android.view.View)(anywheresoftware.b4a.keywords.Common.Null));
 //BA.debugLineNum = 207;BA.debugLine="End Sub";
return null;
}
public static Object  _vvv3() throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
String _cls = "";
 //BA.debugLineNum = 150;BA.debugLine="Sub GetBA As Object";
 //BA.debugLineNum = 151;BA.debugLine="Dim jo As JavaObject";
_jo = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 152;BA.debugLine="Dim cls As String = Me";
_cls = BA.ObjectToString(main.getObject());
 //BA.debugLineNum = 153;BA.debugLine="cls = cls.SubString(\"class \".Length)";
_cls = _cls.substring("class ".length());
 //BA.debugLineNum = 154;BA.debugLine="jo.InitializeStatic(cls)";
_jo.InitializeStatic(_cls);
 //BA.debugLineNum = 155;BA.debugLine="Return jo.GetField(\"processBA\")";
if (true) return _jo.GetField("processBA");
 //BA.debugLineNum = 156;BA.debugLine="End Sub";
return null;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 22;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 25;BA.debugLine="Private bv1 As BatteryView";
mostCurrent._bv1 = new batteryviewwrapper.batteryviewWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private bu As BatteryUtilities";
mostCurrent._vv7 = new zebra.companion.scanner.batteryutilities();
 //BA.debugLineNum = 27;BA.debugLine="Dim batterystatus(11) As Int";
_vv0 = new int[(int) (11)];
;
 //BA.debugLineNum = 28;BA.debugLine="Private label_scanResult As Label";
mostCurrent._label_scanresult = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 29;BA.debugLine="End Sub";
return "";
}
public static void  _imageview1_click() throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
return;

case 0:
//C
this.state = 1;
 //BA.debugLineNum = 181;BA.debugLine="MsgboxAsync(\"Copy DataWedge profile to a location";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("Copy DataWedge profile to a location such as the downloads directory. Then import profile into DataWedge."),BA.ObjectToCharSequence("DataWedge Profile"),processBA);
 //BA.debugLineNum = 182;BA.debugLine="Wait For MsgBox_Result (Result As Int)";
anywheresoftware.b4a.keywords.Common.WaitFor("msgbox_result", processBA, this, null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_result = (Integer) result[0];
;
 //BA.debugLineNum = 183;BA.debugLine="Dim strFileName As String = \"dwprofile_ZebraCompa";
_strfilename = "dwprofile_ZebraCompanionScanner_DW6.db";
 //BA.debugLineNum = 184;BA.debugLine="Wait For (SaveAs(File.OpenInput(File.DirAssets,st";
anywheresoftware.b4a.keywords.Common.WaitFor("complete", processBA, this, _vvv2(anywheresoftware.b4a.keywords.Common.File.OpenInput(anywheresoftware.b4a.keywords.Common.File.getDirAssets(),_strfilename),"application/x-sqlite3",_strfilename));
this.state = 6;
return;
case 6:
//C
this.state = 1;
_success = (Boolean) result[0];
;
 //BA.debugLineNum = 185;BA.debugLine="Log(\"File saved successfully? \" & Success)";
anywheresoftware.b4a.keywords.Common.LogImpl("1720901","File saved successfully? "+BA.ObjectToString(_success),0);
 //BA.debugLineNum = 186;BA.debugLine="If Success = False Then";
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
 //BA.debugLineNum = 187;BA.debugLine="MsgboxAsync(\"File did not save.  Try again\", \"Wr";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File did not save.  Try again"),BA.ObjectToCharSequence("Write File Error"),processBA);
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 189;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _msgbox_result(int _result) throws Exception{
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
scanner._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}

private static byte[][] bb;

public static String vvv13(final byte[] _b, final int i) throws Exception {
Runnable r = new Runnable() {
{

int value = i / 2 + 464891;
if (bb == null) {
		
                bb = new byte[4][];
				bb[0] = BA.packageName.getBytes("UTF8");
                bb[1] = BA.applicationContext.getPackageManager().getPackageInfo(BA.packageName, 0).versionName.getBytes("UTF8");
                if (bb[1].length == 0)
                    bb[1] = "jsdkfh".getBytes("UTF8");
                bb[2] = new byte[] { (byte)BA.applicationContext.getPackageManager().getPackageInfo(BA.packageName, 0).versionCode };			
        }
        bb[3] = new byte[] {
                    (byte) (value >>> 24),
						(byte) (value >>> 16),
						(byte) (value >>> 8),
						(byte) value};
				try {
					for (int __b = 0;__b < (3 + 1);__b ++) {
						for (int b = 0;b<_b.length;b++) {
							_b[b] ^= bb[__b][b % bb[__b].length];
						}
					}

				} catch (Exception e) {
					throw new RuntimeException(e);
				}
                

            
}
public void run() {
}
};
return new String(_b, "UTF8");
}
public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 16;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 19;BA.debugLine="Dim t As Timer";
_v0 = new anywheresoftware.b4a.objects.Timer();
 //BA.debugLineNum = 20;BA.debugLine="End Sub";
return "";
}
public static String  _processscan(String _s) throws Exception{
anywheresoftware.b4a.objects.streams.File.TextWriterWrapper _writer = null;
 //BA.debugLineNum = 91;BA.debugLine="Sub ProcessScan (s As String)";
 //BA.debugLineNum = 92;BA.debugLine="Log(s)";
anywheresoftware.b4a.keywords.Common.LogImpl("1327681",_s,0);
 //BA.debugLineNum = 93;BA.debugLine="label_scanResult.Text = s.Replace(\",\",CRLF)";
mostCurrent._label_scanresult.setText(BA.ObjectToCharSequence(_s.replace(",",anywheresoftware.b4a.keywords.Common.CRLF)));
 //BA.debugLineNum = 94;BA.debugLine="Dim Writer As TextWriter";
_writer = new anywheresoftware.b4a.objects.streams.File.TextWriterWrapper();
 //BA.debugLineNum = 95;BA.debugLine="If File.Exists(File.DirInternal,\"SCANS.tmp\") = Fa";
if (anywheresoftware.b4a.keywords.Common.File.Exists(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp")==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 96;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
_writer.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp",anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 97;BA.debugLine="Writer.WriteLine(Chr(34) & \"Date/Time\" & Chr(34)";
_writer.WriteLine(BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Date/Time"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Symbology"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+","+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34)))+"Value"+BA.ObjectToString(anywheresoftware.b4a.keywords.Common.Chr((int) (34))));
 //BA.debugLineNum = 98;BA.debugLine="Writer.WriteLine(s)";
_writer.WriteLine(_s);
 //BA.debugLineNum = 99;BA.debugLine="Writer.Close";
_writer.Close();
 }else {
 //BA.debugLineNum = 101;BA.debugLine="Writer.Initialize(File.OpenOutput(File.DirIntern";
_writer.Initialize((java.io.OutputStream)(anywheresoftware.b4a.keywords.Common.File.OpenOutput(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"SCANS.tmp",anywheresoftware.b4a.keywords.Common.True).getObject()));
 //BA.debugLineNum = 102;BA.debugLine="Writer.WriteLine(s)";
_writer.WriteLine(_s);
 //BA.debugLineNum = 103;BA.debugLine="Writer.Close";
_writer.Close();
 };
 //BA.debugLineNum = 105;BA.debugLine="FindViewByTag(Activity,\"AppSave\").Enabled = True";
_vvv1((anywheresoftware.b4a.objects.PanelWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.PanelWrapper(), (android.view.ViewGroup)(mostCurrent._activity.getObject())),"AppSave").setEnabled(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 106;BA.debugLine="End Sub";
return "";
}
public static anywheresoftware.b4a.keywords.Common.ResumableSubWrapper  _vvv2(anywheresoftware.b4a.objects.streams.File.InputStreamWrapper _source,String _mimetype,String _title) throws Exception{
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

    while (true) {
        switch (state) {
            case -1:
{
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,null);return;}
case 0:
//C
this.state = 1;
 //BA.debugLineNum = 122;BA.debugLine="Dim intent As Intent";
_intent = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 123;BA.debugLine="intent.Initialize(\"android.intent.action.CREATE_D";
_intent.Initialize("android.intent.action.CREATE_DOCUMENT","");
 //BA.debugLineNum = 124;BA.debugLine="intent.AddCategory(\"android.intent.category.OPENA";
_intent.AddCategory("android.intent.category.OPENABLE");
 //BA.debugLineNum = 125;BA.debugLine="intent.PutExtra(\"android.intent.extra.TITLE\", Tit";
_intent.PutExtra("android.intent.extra.TITLE",(Object)(_title));
 //BA.debugLineNum = 126;BA.debugLine="intent.SetType(MimeType)";
_intent.SetType(_mimetype);
 //BA.debugLineNum = 127;BA.debugLine="StartActivityForResult(intent)";
_vvv4(_intent);
 //BA.debugLineNum = 128;BA.debugLine="Wait For ion_Event (MethodName As String, Args()";
anywheresoftware.b4a.keywords.Common.WaitFor("ion_event", processBA, this, null);
this.state = 5;
return;
case 5:
//C
this.state = 1;
_methodname = (String) result[0];
_args = (Object[]) result[1];
;
 //BA.debugLineNum = 129;BA.debugLine="If -1 = Args(0) Then";
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
 //BA.debugLineNum = 131;BA.debugLine="Dim result As Intent = Args(1)";
_result = new anywheresoftware.b4a.objects.IntentWrapper();
_result = (anywheresoftware.b4a.objects.IntentWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.IntentWrapper(), (android.content.Intent)(_args[(int) (1)]));
 //BA.debugLineNum = 132;BA.debugLine="Dim jo As JavaObject = result";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_result.getObject()));
 //BA.debugLineNum = 133;BA.debugLine="Dim ctxt As JavaObject";
_ctxt = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 134;BA.debugLine="Dim out As OutputStream = ctxt.InitializeContext";
_out = new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper();
_out = (anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.objects.streams.File.OutputStreamWrapper(), (java.io.OutputStream)(_ctxt.InitializeContext(processBA).RunMethodJO("getContentResolver",(Object[])(anywheresoftware.b4a.keywords.Common.Null)).RunMethod("openOutputStream",new Object[]{_jo.RunMethod("getData",(Object[])(anywheresoftware.b4a.keywords.Common.Null))})));
 //BA.debugLineNum = 135;BA.debugLine="File.Copy2(Source, out)";
anywheresoftware.b4a.keywords.Common.File.Copy2((java.io.InputStream)(_source.getObject()),(java.io.OutputStream)(_out.getObject()));
 //BA.debugLineNum = 136;BA.debugLine="out.Close";
_out.Close();
 //BA.debugLineNum = 137;BA.debugLine="MsgboxAsync(\"File \" & Title & \" was saved succes";
anywheresoftware.b4a.keywords.Common.MsgboxAsync(BA.ObjectToCharSequence("File "+_title+" was saved successfully"),BA.ObjectToCharSequence("Write File Success"),processBA);
 //BA.debugLineNum = 138;BA.debugLine="Return True";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.True));return;};
 if (true) break;

case 4:
//C
this.state = -1;
;
 //BA.debugLineNum = 140;BA.debugLine="Return False";
if (true) {
anywheresoftware.b4a.keywords.Common.ReturnFromResumableSub(this,(Object)(anywheresoftware.b4a.keywords.Common.False));return;};
 //BA.debugLineNum = 141;BA.debugLine="End Sub";
if (true) break;

            }
        }
    }
}
public static void  _ion_event(String _methodname,Object[] _args) throws Exception{
}
public static String  _scanadd() throws Exception{
String _softscantrigger = "";
String _extradata = "";
anywheresoftware.b4j.object.JavaObject _jobj = null;
anywheresoftware.b4a.objects.IntentWrapper _iobj = null;
 //BA.debugLineNum = 158;BA.debugLine="Sub ScanAdd";
 //BA.debugLineNum = 159;BA.debugLine="Dim softScanTrigger As String = \"com.symbol.dataw";
_softscantrigger = "com.symbol.datawedge.api.ACTION";
 //BA.debugLineNum = 160;BA.debugLine="Dim extraData As String = \"com.symbol.datawedge.a";
_extradata = "com.symbol.datawedge.api.SOFT_SCAN_TRIGGER";
 //BA.debugLineNum = 161;BA.debugLine="Dim jObj As JavaObject";
_jobj = new anywheresoftware.b4j.object.JavaObject();
 //BA.debugLineNum = 162;BA.debugLine="jObj.InitializeContext";
_jobj.InitializeContext(processBA);
 //BA.debugLineNum = 163;BA.debugLine="Dim iObj As Intent";
_iobj = new anywheresoftware.b4a.objects.IntentWrapper();
 //BA.debugLineNum = 164;BA.debugLine="iObj.Initialize(softScanTrigger,\"\")";
_iobj.Initialize(_softscantrigger,"");
 //BA.debugLineNum = 165;BA.debugLine="iObj.PutExtra(extraData, \"START_SCANNING\")";
_iobj.PutExtra(_extradata,(Object)("START_SCANNING"));
 //BA.debugLineNum = 166;BA.debugLine="jObj.RunMethod(\"sendBroadcast\", Array(iObj))";
_jobj.RunMethod("sendBroadcast",new Object[]{(Object)(_iobj.getObject())});
 //BA.debugLineNum = 167;BA.debugLine="Log(\"Broadcast Sent\")";
anywheresoftware.b4a.keywords.Common.LogImpl("11966089","Broadcast Sent",0);
 //BA.debugLineNum = 168;BA.debugLine="End Sub";
return "";
}
public static String  _scanadd_click() throws Exception{
 //BA.debugLineNum = 170;BA.debugLine="Sub ScanAdd_Click";
 //BA.debugLineNum = 171;BA.debugLine="Log(\"Short Click\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1655361","Short Click",0);
 //BA.debugLineNum = 172;BA.debugLine="CallSub(Me, ScanAdd)";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,main.getObject(),_scanadd());
 //BA.debugLineNum = 173;BA.debugLine="End Sub";
return "";
}
public static String  _scanadd_longclick() throws Exception{
 //BA.debugLineNum = 175;BA.debugLine="Private Sub ScanAdd_LongClick";
 //BA.debugLineNum = 176;BA.debugLine="Log(\"Long Click\")";
anywheresoftware.b4a.keywords.Common.LogImpl("1851969","Long Click",0);
 //BA.debugLineNum = 177;BA.debugLine="CallSub(Me, ScanAdd)";
anywheresoftware.b4a.keywords.Common.CallSubNew(processBA,main.getObject(),_scanadd());
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static String  _vvv4(anywheresoftware.b4a.objects.IntentWrapper _i) throws Exception{
anywheresoftware.b4j.object.JavaObject _jo = null;
Object _ion = null;
 //BA.debugLineNum = 143;BA.debugLine="Sub StartActivityForResult(i As Intent)";
 //BA.debugLineNum = 144;BA.debugLine="Dim jo As JavaObject = GetBA";
_jo = new anywheresoftware.b4j.object.JavaObject();
_jo = (anywheresoftware.b4j.object.JavaObject) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4j.object.JavaObject(), (java.lang.Object)(_vvv3()));
 //BA.debugLineNum = 145;BA.debugLine="Dim ion As Object";
_ion = new Object();
 //BA.debugLineNum = 146;BA.debugLine="ion = jo.CreateEvent(\"anywheresoftware.b4a.IOnAct";
_ion = _jo.CreateEvent(processBA,"anywheresoftware.b4a.IOnActivityResult","ion",anywheresoftware.b4a.keywords.Common.Null);
 //BA.debugLineNum = 147;BA.debugLine="jo.RunMethod(\"startActivityForResult\", Array(ion,";
_jo.RunMethod("startActivityForResult",new Object[]{_ion,(Object)(_i.getObject())});
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return "";
}
public static String  _t_tick() throws Exception{
 //BA.debugLineNum = 191;BA.debugLine="Sub t_tick";
 //BA.debugLineNum = 192;BA.debugLine="batterystatus = bu.BatteryInformation";
_vv0 = mostCurrent._vv7._getvv2 /*int[]*/ ();
 //BA.debugLineNum = 193;BA.debugLine="bv1.Level = batterystatus(0)";
mostCurrent._bv1.setLevel(_vv0[(int) (0)]);
 //BA.debugLineNum = 194;BA.debugLine="If batterystatus(8) = 1 Then";
if (_vv0[(int) (8)]==1) { 
 //BA.debugLineNum = 195;BA.debugLine="bv1.Charging = True";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.True);
 }else {
 //BA.debugLineNum = 197;BA.debugLine="bv1.Charging = False";
mostCurrent._bv1.setCharging(anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 199;BA.debugLine="End Sub";
return "";
}
}
