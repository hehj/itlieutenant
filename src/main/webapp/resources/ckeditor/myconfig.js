/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	config.toolbar = 'Weixin';
	config.height = 500;
	config.toolbar_Basic =
	[
		['Maximize'],   
		['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],  
		['Button','Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak'],  
		'/',  
		['Styles','Format'],  
		['Bold','Italic','Strike'],  
		['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'],  
		['hello','Link','Unlink','Anchor']
	];
	config.toolbar_Weixin = 
	[
	 ['Undo','Redo','-','FontSize','-','Blockquote','HorizontalRule'],
	 ['Bold','Italic','Underline','TextColor','BGColor'],  
	 ['Outdent','Indent','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','NumberedList','BulletedList'],
	 ['Image','Flash']
	];
};

CKEDITOR.config.allowedContent = true;