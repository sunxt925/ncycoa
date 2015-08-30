/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.image_previewText = ' '; // 去掉图片预览中的鸟语，这里注意里面一定要有个空格  
	var pathName = "/anyue";
    config.filebrowserUploadUrl = pathName+"/notice!get.action"; // 指定上传的目标地址
    config.filebrowserImageBrowseUrl="/anyue";
    config.filebrowserImageUploadUrl =pathName+"/notice!get.action";
    //var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/uimcardprj
    //var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    //config.filebrowserImageUploadUrl = projectName+'/system/upload.do'; //固定路径
};
