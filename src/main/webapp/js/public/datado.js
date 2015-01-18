/*
功能：税务局、税务所和税管员下拉列表的连动
参数：DwObj 保存单位信息的下拉列表，如果为null则表示没有单位下拉列表
	 BmObj 保存部门信息的下拉列表，必填项
	 RyObj 保存人员信息的下拉列表，必填项
	 Jbm   局编码，如果DwObj为null，则该值不能为空，否则可以为空
	 Xsj   是否显示单位下拉列表
*/
function DwBmRyObj(DwObj,BmObj,RyObj,Jbm,Xsj)
{
	this.ErrorFlag=true;//错误标志
	if((DwObj=="" || DwObj==null || DwObj==undefined) && (Jbm=="" || Jbm==null || Jbm==undefined)) return;
	if(BmObj=="" || BmObj==null || BmObj==undefined) return;
	if(RyObj=="" || RyObj==null || RyObj==undefined) return;
	this.ErrorFlag=false;
	this.Xsj=Xsj;//是否显示局列表
	this.DwObj=DwObj;//单位下拉列表对象
	this.BmObj=BmObj;//部门下拉列表对象
	this.RyObj=RyObj;//人员下拉列表对象
	if(DwObj!="" && DwObj!=null && DwObj!=undefined && Jbm=="")
		this.Jbm="";
	else
		this.Jbm=Jbm;//当前局编码
	this.Bmbm="";//当前部门编码
	this.Rybm="";//当前人员编码
	this.DwDo=DwChangeDo;//改变单位下拉列表的值动作
	this.BmDo=BmChangeDo;//改变部门下拉列表的值动作
	this.RyDo=RyChangeDo;//改变人员下拉列表的值动作
	this.DispDwList=LoadDwData;//加载单位下拉列表数据
}
function remoteTimeout(message)
{
	remoteCall--;
	top.MessageBox.Show(null,"远程调用出错或超时:"+message,null,"OK","Error");
}
//功能：加载单位下拉列表数据
function LoadDwData()
{
	//取得单位数据
	if(this.Xsj)
	{
		if(this.DwObj!="" && this.DwObj!=null && this.DwObj!=undefined && this.Jbm=="")
			bmryDao.getAllJbm("",{callback:GetDwData,timeout:5000,errorHandler:remoteTimeout,async:false});
		else
			bmryDao.getAllJbm(this.Jbm,{callback:GetDwData,timeout:5000,errorHandler:remoteTimeout,async:false});
	}
	else
		bmryDao.getAllBmbm(this.Jbm,{callback:GetBmData,timeout:5000,errorHandler:remoteTimeout,async:false});

}

//功能：把从服务器读取的数据显示下拉列表中
function DispList(Obj,DataObj)
{
	//加载现有数据
	var oOption = document.createElement("OPTION");
	oOption.text="请选择...";
	oOption.value="";
	Obj.add(oOption);
	for(var i=0;i<DataObj.length;i++)
	{
		var oOption = document.createElement("OPTION");
		oOption.text=DataObj[i].bmnr;
		oOption.value=DataObj[i].bm;
		Obj.add(oOption);
	}
}

//功能:	删除下拉列表数据
function DelList(ListObj)
{
	for(var i=ListObj.length;i>=0;i--)
		ListObj.remove(i);
}
//功能:从服务器得到单位编码数据,DWR CallBack函数
function GetDwData(data)
{
	DelList(NewDwBmRyObj.DwObj);
	DispList(NewDwBmRyObj.DwObj,data);
}
//功能:根据单位编码从服务器得到部门编码数据,DWR CallBack函数
function GetBmData(data)
{
	DelList(NewDwBmRyObj.BmObj);
	DispList(NewDwBmRyObj.BmObj,data);
}
//功能:根据单位编码和部门编码从服务器得到部门编码数据,DWR CallBack函数
function GetRyData(data)
{
	DelList(NewDwBmRyObj.RyObj);
	DispList(NewDwBmRyObj.RyObj,data);
}

function DwChangeDo()//改变单位下拉列表的值动作
{
	if(!this.ErrorFlag)
	{
		this.Jbm=this.DwObj.value;
		if(this.Jbm=="")
		{
			DelList(NewDwBmRyObj.BmObj);
			DelList(NewDwBmRyObj.RyObj);
		}
		else
		{
			bmryDao.getAllBmbm(this.Jbm,{callback:GetBmData,timeout:5000,errorHandler:remoteTimeout,async:false});
			DelList(NewDwBmRyObj.RyObj);
		}
	}
}

function BmChangeDo()//改变部门下拉列表的值动作
{
	if(!this.ErrorFlag)
	{
		this.Bmbm=this.BmObj.value;
		if(this.Bmbm=="")
			DelList(NewDwBmRyObj.RyObj);
		else
			bmryDao.getAllYhbm(this.Jbm,this.Bmbm,{callback:GetRyData,timeout:5000,errorHandler:remoteTimeout,async:false});
	}
}

function RyChangeDo()//改变人员下拉列表的值动作
{
	if(!this.ErrorFlag)
		this.Rybm=this.RyObj.value;
}
