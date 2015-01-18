/*
公共处理JavaScript程序：搜索框内显示默认内容，获得焦点时默认内容清空
*/


function cls(){
with(event.srcElement)
if(value==defaultValue) value=""
}
function res(){
with(event.srcElement)
if(value=="") value=defaultValue
}