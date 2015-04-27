/*
���ܣ�˰��֡�˰������˰��Ա�����б������
������DwObj ���浥λ��Ϣ�������б����Ϊnull���ʾû�е�λ�����б�
	 BmObj ���沿����Ϣ�������б�������
	 RyObj ������Ա��Ϣ�������б�������
	 Jbm   �ֱ��룬���DwObjΪnull�����ֵ����Ϊ�գ��������Ϊ��
	 Xsj   �Ƿ���ʾ��λ�����б�
*/
function DwBmRyObj(DwObj,BmObj,RyObj,Jbm,Xsj)
{
	this.ErrorFlag=true;//�����־
	if((DwObj=="" || DwObj==null || DwObj==undefined) && (Jbm=="" || Jbm==null || Jbm==undefined)) return;
	if(BmObj=="" || BmObj==null || BmObj==undefined) return;
	if(RyObj=="" || RyObj==null || RyObj==undefined) return;
	this.ErrorFlag=false;
	this.Xsj=Xsj;//�Ƿ���ʾ���б�
	this.DwObj=DwObj;//��λ�����б����
	this.BmObj=BmObj;//���������б����
	this.RyObj=RyObj;//��Ա�����б����
	if(DwObj!="" && DwObj!=null && DwObj!=undefined && Jbm=="")
		this.Jbm="";
	else
		this.Jbm=Jbm;//��ǰ�ֱ���
	this.Bmbm="";//��ǰ���ű���
	this.Rybm="";//��ǰ��Ա����
	this.DwDo=DwChangeDo;//�ı䵥λ�����б��ֵ����
	this.BmDo=BmChangeDo;//�ı䲿�������б��ֵ����
	this.RyDo=RyChangeDo;//�ı���Ա�����б��ֵ����
	this.DispDwList=LoadDwData;//���ص�λ�����б�����
}
function remoteTimeout(message)
{
	remoteCall--;
	top.MessageBox.Show(null,"Զ�̵��ó����ʱ:"+message,null,"OK","Error");
}
//���ܣ����ص�λ�����б�����
function LoadDwData()
{
	//ȡ�õ�λ����
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

//���ܣ��Ѵӷ�������ȡ��������ʾ�����б���
function DispList(Obj,DataObj)
{
	//������������
	var oOption = document.createElement("OPTION");
	oOption.text="��ѡ��...";
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

//����:	ɾ�������б�����
function DelList(ListObj)
{
	for(var i=ListObj.length;i>=0;i--)
		ListObj.remove(i);
}
//����:�ӷ������õ���λ��������,DWR CallBack����
function GetDwData(data)
{
	DelList(NewDwBmRyObj.DwObj);
	DispList(NewDwBmRyObj.DwObj,data);
}
//����:���ݵ�λ����ӷ������õ����ű�������,DWR CallBack����
function GetBmData(data)
{
	DelList(NewDwBmRyObj.BmObj);
	DispList(NewDwBmRyObj.BmObj,data);
}
//����:���ݵ�λ����Ͳ��ű���ӷ������õ����ű�������,DWR CallBack����
function GetRyData(data)
{
	DelList(NewDwBmRyObj.RyObj);
	DispList(NewDwBmRyObj.RyObj,data);
}

function DwChangeDo()//�ı䵥λ�����б��ֵ����
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

function BmChangeDo()//�ı䲿�������б��ֵ����
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

function RyChangeDo()//�ı���Ա�����б��ֵ����
{
	if(!this.ErrorFlag)
		this.Rybm=this.RyObj.value;
}
