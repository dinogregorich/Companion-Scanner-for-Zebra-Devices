B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes 
	#StartAtBoot: False
	
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.
	Dim BC As BroadCastReceiver
	Dim structure As List
	Dim data As Map
End Sub

Sub Service_Create
	'the parameters may discovered with Log(intent.extrasToString) in the OnReceive Method
	BC.Initialize("BC")
	structure.Initialize()
	structure.Add("com.symbol.datawedge.source")
	structure.Add("com.symbol.datawedge.data_string")
	structure.Add("com.symbol.datawedge.label_type")
	structure.Add("com.symbol.datawedge.decode_data")
	structure.Add("com.symbol.datawedge.data_dispatch_time")
	structure.Add("com.symbol.datawedge.decoded_mode")
	structure.Add("com.motorolasolutions.emdk.datawedge.source")
	structure.Add("com.motorolasolutions.emdk.datawedge.data_string")
	structure.Add("com.motorolasolutions.emdk.datawedge.label_type")
	structure.Add("com.motorolasolutions.emdk.datawedge.decode_data")
	structure.Add("com.symbol.datawedge.api")
End Sub

Sub Service_Start (StartingIntent As Intent)
	'category and action must match the settings in datawedge app
	BC.addCategory("intent_category")
	BC.addAction("scanner_intent")
	BC.SetPriority(20000000)
	BC.registerReceiver("scanner_intent")
	Log("Scanner Service Started: " & StartingIntent.ExtrasToString)
	Service.StopAutomaticForeground 'Call this when the background task completes (if there is one)
End Sub

Sub Service_Destroy

End Sub

Sub BC_OnReceive (action As String, o As Object)
	'building a "dictionary" with last suffix to eliminate duplicates
	Dim intent As Intent = o
	data.Initialize()
	For Each l As String In structure
		If intent.HasExtra(l) Then
			data.Put(l.SubString(l.LastIndexOf(".")+1), intent.GetExtra(l))
		End If
	Next
	Dim strSym As String = data.Get("label_type")
	strSym = strSym.ToUpperCase
	strSym = strSym.Replace("LABEL-TYPE-","")
	DateTime.DateFormat="yyyy-MM-dd HH:mm:ss"
	Dim strAll As String = Chr(34) & DateTime.Date(DateTime.now) & Chr(34) & "," & Chr(34) & strSym & Chr(34) & "," & Chr(34) & data.Get("data_string") & Chr(34)
	
	CallSubDelayed2(Main, "ProcessScan", strAll)
End Sub