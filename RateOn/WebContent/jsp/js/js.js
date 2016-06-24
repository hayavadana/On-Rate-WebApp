function MM_openBrWindow(theURL,winName,features) {
  window.open(theURL,winName,features);
}

function showme2(e)
{
el=document.getElementById(e);
el.style.display="block";
}
function hideme2(e)
{
el=document.getElementById(e);
el.style.display="none";
}

var e2="tab1";

function changetc(e){
	document.getElementById(e2+'_tc').className='send_tab_bor';
	document.getElementById(e2+'_lb').className='send_tab_bor';
	document.getElementById(e2+'_rb').className='send_tab_bor';
	document.getElementById(e2+'_cc').className='send_tab_tc';
	document.getElementById(e2+'_bl').className='send_tab_bl';
	document.getElementById(e2+'_bc').className='send_tab_bb';
	document.getElementById(e2+'_br').className='send_tab_br';
	
	document.getElementById(e+'_tc').className='send_tab_bor_sel';
	document.getElementById(e+'_lb').className='send_tab_bor_sel';
	document.getElementById(e+'_rb').className='send_tab_bor_sel';
	document.getElementById(e+'_cc').className='send_tab_tc_sel';
	document.getElementById(e+'_bl').className='send_tab_bl_sel';
	document.getElementById(e+'_bc').className='send_tab_bb_sel';
	document.getElementById(e+'_br').className='send_tab_br_sel';
	e2=e;	
}

function active(e) {
document.getElementById(e).disabled=false;
}

function deactive(e) {
document.getElementById(e).disabled=true;
}

function changeval(e1,e2){
	document.getElementById(e1).value=e2;
}
function edit_text() {
var tag_name=document.getElementsByTagName("input");
for(i=0;i<tag_name.length;i++)
{
	
	tag_name[i].disabled=false;
	tag_name[i].readOnly=false;
}
}
function edit_sel() {
var tag_name=document.getElementsByTagName("select");
for(i=0;i<tag_name.length;i++)
{
	tag_name[i].disabled=false;
}
}
function edit_textarea() {
var tag_name=document.getElementsByTagName("textarea");
for(i=0;i<tag_name.length;i++)
{
	tag_name[i].disabled=false;
}
}
function disable_text() {
var tag_name=document.getElementsByTagName("input");
for(i=0;i<tag_name.length;i++)
{
	
	tag_name[i].disabled=true;
	tag_name[i].readOnly=true;
}
}
function disable_sel() {
var tag_name=document.getElementsByTagName("select");
for(i=0;i<tag_name.length;i++)
{
	tag_name[i].disabled=true;
}
}

var no=new Array();

function copySelectedOptions(sou,des) {
var source=document.getElementById(sou);
var destination=document.getElementById(des);
var opt=document.createElement('option');
var last=destination.length;

//alert(source.length);
for (var i=0;i<source.length;i++) {
//alert(i);
	if(source.options[i].selected)
	{
		opt.text=source.options[i].text;
		destination.options[last]=new Option(opt.text, "", false, false)
		last=destination.length;
		t=no.length;
		//alert(t)
		no[t]=source.options[i].text;
		//alert(no.length)
		//source.remove(i);
		
	}
	//no=source.length;
}
}
function selRemove(sou) {
	var source=document.getElementById(sou);
	for(i=0;i<no.length;i++)
	{
		for(j=0;j<source.length;j++)
		{
			if(no[i]==source.options[j].text)
			{
				source.remove(j);
				continue;
			}
		}
	}
	no=new Array();
}
function copySelectedOptions(sou,des) {

var source=document.getElementById(sou);
var destination=document.getElementById(des);
var opt=document.createElement('option');
var last=destination.length;

//alert(source.length);
for (var i=0;i<source.length;i++) {
//alert(i);
	if(source.options[i].selected)
	{
		opt.text=source.options[i].text;
		destination.options[last]=new Option(opt.text, "", false, false)
		last=destination.length;
		t=no.length;
		//alert(t)
		no[t]=source.options[i].text;
		//alert(no.length)
		//source.remove(i);
		
	}
	//no=source.length;
}
}
function selRemove(sou) {
	var source=document.getElementById(sou);
	for(i=0;i<no.length;i++)
	{
		for(j=0;j<source.length;j++)
		{
			if(no[i]==source.options[j].text)
			{
				source.remove(j);
				continue;
			}
		}
	}
	no=new Array();
}


