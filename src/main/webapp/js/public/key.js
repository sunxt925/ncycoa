/*
��������JavaScript���򣺰�������
*/

/*
���ܣ�ϵͳ��ݼ�������
���ã��ڿͻ���ʵ��ĳһ�������簴��F2����ʾһ��Ϣ��function F2(){alert("������F2����");}
*/

function KeyDownFun()
{
	this.KeyCodeValue=new Array();//��ݼ�keyCodeֵ����
	this.KeyCodeValue[0]=113;//F2��
	this.KeyCodeValue[1]=114;//F3��
	this.KeyCodeValue[2]=115;//F4��
	this.KeyCodeValue[3]=116;//F5��
	this.KeyCodeValue[4]=117;//F6��
	this.KeyCodeValue[5]=118;//F7��
	this.KeyCodeValue[6]=119;//F8��
	this.KeyCodeValue[7]=120;//F9��
	this.KeyCodeValue[8]=121;//F10��
	this.KeyCodeValue[9]=122;//F11��
	this.KeyCodeValue[10]=123;//F12��

	this.KeyCodeFun=new Array();//��Ӧ��ݼ���ִ�еķ���
	this.KeyCodeFun[0]="F2()";//����F2������F2()����
	this.KeyCodeFun[1]="F3()";//����F3������F3()����
	this.KeyCodeFun[2]="F4()";//����F4������F4()����
	this.KeyCodeFun[3]="F5()";//����F5������F5()����
	this.KeyCodeFun[4]="F6()";//����F6������F6()����
	this.KeyCodeFun[5]="F7()";//����F7������F7()����
	this.KeyCodeFun[6]="F8()";//����F8������F8()����
	this.KeyCodeFun[7]="F9()";//����F9������F9()����
	this.KeyCodeFun[8]="F10()";//����F10������F10()����
	this.KeyCodeFun[9]="F11()";//����F11������F11()����
	this.KeyCodeFun[10]="F12()";//����F12������F12()����
	
	this.NowKeyCodeValue=0;//��ǰ������ֵ
	this.NowKeyDownDo=KeyDoFun//����ִ�з���
}

function KeyDoFun()//����ִ�з���
{
	var i;
	for(i=0;i<this.KeyCodeValue.length;i++)
	{
		if(this.NowKeyCodeValue==this.KeyCodeValue[i])
		{
			try
			{
				eval(this.KeyCodeFun[i]);
			}
			catch(e)
			{}
			finally 
			{
				this.NowKeyCodeValue=0;
				event.keyCode=0;
				event.returnValue=false;
			}
		}
	}
}

var KeyDo=new KeyDownFun();//�½�һ������

document.onkeydown=function()
{
	KeyDo.NowKeyCodeValue=event.keyCode;//�õ���ǰ����ֵ
	KeyDo.NowKeyDownDo();//����
}


/*
���ܣ����س����ƶ����㵽��һ���ؼ���ͬʱ����ִ��ĳһ����
*/
function EnterKeyDo(DoFunStr)
{
	if(event.keyCode==13)//������»س���
	{
		if(DoFunStr!="")//����������ַ�����Ϊ��
			eval(DoFunStr);//ִ�иú���
		event.keyCode=9;
		event.returnValue=true;
	}
}

/*
���ܣ���������Ҽ�
*/

document.oncontextmenu = function() { return false;} 