/**
 * imagebox - jQuery plugins
 * 
 * Copyright 2015 hehangjie [ hehangjie@hotmail.com ]
 * 
 * Dependencies: fancybox
 */
(function($) {
	$.fn.imagebox = function(method) {
		
		var $this = $(this);
		
		var methods = {
			init : function(dataOptions, cssOptions) {
				var dataDefaults = {
					requestUrl : '',
					idField : 'id',
					urlField : 'url',
					descriptionField : 'description',
					menuArray : [ {
						"key" : "EDIT",
						"bindFunction" : "imageEdit"
					}, {
						"key" : "DELETE",
						"bindFunction" : "imageDel"
					} ]
				};
				var cssDefaults = {
					checkbox : 'hiden',
					background : '#EFEFEF',
					borderBottom : '1px dashed #ccc',
					padding : '8px 8px 8px 8px',
					width : '160px',
					height : '140px',
					float : 'left',
				};

				var cssOpts = $.extend(cssDefaults, cssOptions);
				var dataOpts = $.extend(dataDefaults, dataOptions);

				$.ajax({
					url : dataOpts.requestUrl,
					context : document.body,
					success : function(data, type) {
						if(""==data.result||null==data.result)
							return;
						rows = data.result.result;
						var html = "";
						$.each(
								rows,
							function(idx, item) {
								if (item == undefined || item.key== undefined)
									return;
								html += '<div class="imagebox">';
								// a img begin
								html += '<a rel="images_group" href="http://imgs.itlieutenant.com/';
								html += item.key;
								html += '" title="'+ item.key+ '"> <img alt="" src="http://blog-imgs.img-cn-shenzhen.aliyuncs.com/';
								html += item.key+'@160h_140w_2e';
								html += '" width="'+ cssOpts.width+ '" height="'+ cssOpts.height+ '" /></a>';
								// a img end
								// menu DIV Begin
								html += '<div class="absmenu"><ul class="absul">';
								
								$.each(
									dataOpts.menuArray,
									function(idy,menu) {
										if (menu == undefined || menu.key == undefined)
											return;
										html += '<li class="absli"><a href="#" onclick="'+ menu.bindFunction+ '('+ (parseInt(idx)+parseInt(1))+ ')">'+ menu.key+ '</a></li>';
									});
								html += '</ul></div>';
								// menu DIV End
								// checkbox begin
								html += '<div class="abschk">';
								html += '<input name="chkItem" type="checkbox" value="'+ item.key+ '" />';
								html += '</div>';
								// checkbox end
								html += '</div>';
							});

							$this.html(html);
							var $imagetoolbar = $('.imagetoolbar');
							$imagetoolbar.css("padding", cssOpts.padding);
							$imagetoolbar.css("border-bottom",cssOpts.borderBottom);
	
							var $imagebox = $('.imagebox');
							$imagebox.css("width", cssOpts.width);
							$imagebox.css("height", cssOpts.height);
							$imagebox.css("background", cssOpts.background);
							$imagebox.css("padding", cssOpts.padding);
							$imagebox.css("border-bottom",cssOpts.borderBottom);
							$imagebox.css("float", cssOpts.float);
							$imagebox.css("position", "relative");
	
							var $absmenu = $('.absmenu');
							$absmenu.css("position", "absolute");
							$absmenu.css("background", '#CCCCFF');
							$absmenu.css("width", "90%");
							$absmenu.css("left", "9px");
							$absmenu.css("bottom", "10px");
							$absmenu.css("filter", "alpha(opacity=45)");
							$absmenu.css("opacity", "0.45");
							$absmenu.hide();
	
							var $absul = $('.absul');
							$absul.css("font-size", "5px");
							$absul.css("list-style-type", "none");
							
							var $absli = $('.absli');
							$absli.css("margin-bottom", "5px");
							$absli.css("margin-left", "20px");
	
							var $abschk = $('.abschk');
							$abschk.css("position", "absolute");
							$abschk.css("left", "10px");
							$abschk.css("top", "10px");
	
							if (cssOpts.checkbox != 'show') {
								$imagetoolbar.hide();
								$abschk.hide();
							}
	
							$('a[rel=images_group]').fancybox({
									'transitionIn' : 'fade',
									'transitionOut' : 'fade',
									'titlePosition' : 'over',
									'titleFormat' : function(title,currentArray,currentIndex,currentOpts) { // 可以自定义标题的格式
										return '<span id="fancybox-title-over">Image '
												+ (currentIndex + 1)
												+ ' / '
												+ currentArray.length
												+ (title.length ? ' &nbsp; '
														+ title
														: '')
												+ '</span>';
									}
							});
	
							$(".imagebox").mouseenter(function() {
								var li = $(this).find(".absmenu .absul li");
								if (li.length > 0)
									$(this).find(".absmenu").show();
								$(this).css("background","#9999FF");
							});
	
							$(".imagebox").mouseleave(function() {
								$(this).css("background", "#EFEFEF");
								$(this).find(".absmenu").hide();
							});
							
							return rows;
						}
				});
			},
			checkAll : function() {
				$("[name = chkItem]:checkbox").attr("checked", true);
			},
			checkNone : function() {
				$("[name = chkItem]:checkbox").attr("checked", false);
			}
		};
		// 方法调用
		if (methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(
					arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method' + method + 'does not exist on juery.imagebox');
		}

	};

})(jQuery);