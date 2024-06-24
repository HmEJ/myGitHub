// JavaScript Document
$(document).ready(function () {

	//性别
	$('.sex').click(function(){
	   if($(this).attr("class")=="sex on"){
	      $('.sex').addClass('on');
		  $(this).removeClass('on');
	   }else
	   {
		  $('.sex').removeClass('on');
		  $(this).addClass('on');
	   }
	})
	
	//相关工作经验
	$('.typeJob').click(function(){
	   if($(this).attr("class")=="typeJob on"){
	      $('.typeJob').addClass('on');
		  $(this).removeClass('on');
	   }else
	   {
		  $('.typeJob').removeClass('on');
		  $(this).addClass('on');
	   }
	})
	
  //列表摘要
  $('.seachlist .tj1 span.list').click(function(){
      $('.seachlist .tj1').removeClass('on');
	  $(this).addClass('on');
	  $('.seachlist .tj1 span.zhaoyao').removeClass('on');
	  $('table.listtab tr.xxdetail').addClass('none')
  })
  $('.seachlist .tj1 span.zhaoyao').click(function(){
      $('.seachlist .tj1').addClass('on');
	  $(this).addClass('on');
	  $('.seachlist .tj1 span.list').removeClass('on');
	  $('table.listtab tr.xxdetail').removeClass('none');
  })
  
  
})