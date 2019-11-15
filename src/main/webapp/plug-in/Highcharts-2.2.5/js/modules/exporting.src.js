(function(){var Z=Highcharts,H=Z.Chart,L=Z.addEvent,J=Z.removeEvent,M=Z.createElement,K=Z.discardElement,O=Z.css,U=Z.merge,X=Z.each,G=Z.extend,Y=Math,P=Y.max,A=document,V=window,F=A.documentElement.ontouchstart!==undefined,S="M",R="L",N="div",I="hidden",Q="none",W="highcharts-",C="absolute",T="px",B,E=Z.getOptions();G(E.lang,{downloadPNG:"下载PNG图片",downloadJPEG:"下载JPEG图片",downloadPDF:"下载PDF文件",downloadSVG:"下载SVG矢量图片",exportButtonTitle:"导出raster或者vector图片",printButtonTitle:"打印图片"});E.navigation={menuStyle:{border:"1px solid #A0A0A0",background:"#FFFFFF"},menuItemStyle:{padding:"0 5px",background:Q,color:"#303030",fontSize:F?"14px":"11px"},menuItemHoverStyle:{background:"#4572A5",color:"#FFFFFF"},buttonOptions:{align:"right",backgroundColor:{linearGradient:[0,0,0,20],stops:[[0.4,"#F7F7F7"],[0.6,"#E3E3E3"]]},borderColor:"#B0B0B0",borderRadius:3,borderWidth:1,height:20,hoverBorderColor:"#909090",hoverSymbolFill:"#81A7CF",hoverSymbolStroke:"#4572A5",symbolFill:"#E0E0E0",symbolStroke:"#A0A0A0",symbolX:11.5,symbolY:10.5,verticalAlign:"top",width:24,y:10}};E.exporting={type:"image/png",url:"http://export.highcharts.com/",width:800,buttons:{exportButton:{symbol:"exportIcon",x:-10,symbolFill:"#A8BF77",hoverSymbolFill:"#768F3E",_id:"exportButton",_titleKey:"exportButtonTitle",menuItems:[{textKey:"downloadPNG",onclick:function(){this.exportChart()}},{textKey:"downloadJPEG",onclick:function(){this.exportChart({type:"image/jpeg"})}},{textKey:"downloadPDF",onclick:function(){this.exportChart({type:"application/pdf"})}},{textKey:"downloadSVG",onclick:function(){this.exportChart({type:"image/svg+xml"})}}]},printButton:{symbol:"printIcon",x:-36,symbolFill:"#B5C9DF",hoverSymbolFill:"#779ABF",_id:"printButton",_titleKey:"printButtonTitle",onclick:function(){this.print()}}}};G(H.prototype,{getSVG:function(f){var e=this,d,c,a,b,g=U(e.options,f);if(!A.createElementNS){A.createElementNS=function(h,i){return A.createElement(i)}}c=M(N,null,{position:C,top:"-9999em",width:e.chartWidth+T,height:e.chartHeight+T},A.body);G(g.chart,{renderTo:c,forExport:true});g.exporting.enabled=false;g.chart.plotBackgroundImage=null;g.series=[];X(e.series,function(h){b=U(h.options,{animation:false,showCheckbox:false,visible:h.visible});if(!b.isInternal){if(b&&b.marker&&/^url\(/.test(b.marker.symbol)){b.marker.symbol="circle"}g.series.push(b)}});d=new Highcharts.Chart(g);X(["xAxis","yAxis"],function(h){X(e[h],function(j,l){var k=d[h][l],n=j.getExtremes(),i=n.userMin,m=n.userMax;if(i!==B||m!==B){k.setExtremes(i,m,true,false)}})});a=d.container.innerHTML;g=null;d.destroy();K(c);a=a.replace(/zIndex="[^"]+"/g,"").replace(/isShadow="[^"]+"/g,"").replace(/symbolName="[^"]+"/g,"").replace(/jQuery[0-9]+="[^"]+"/g,"").replace(/isTracker="[^"]+"/g,"").replace(/url\([^#]+#/g,"url(#").replace(/<svg /,'<svg xmlns:xlink="http://www.w3.org/1999/xlink" ').replace(/ href=/g," xlink:href=").replace(/\n/," ").replace(/<\/svg>.*?$/,"</svg>").replace(/&nbsp;/g,"\u00A0").replace(/&shy;/g,"\u00AD").replace(/<IMG /g,"<image ").replace(/height=([^" ]+)/g,'height="$1"').replace(/width=([^" ]+)/g,'width="$1"').replace(/hc-svg-href="([^"]+)">/g,'xlink:href="$1"/>').replace(/id=([^" >]+)/g,'id="$1"').replace(/class=([^" ]+)/g,'class="$1"').replace(/ transform /g," ").replace(/:(path|rect)/g,"$1").replace(/style="([^"]+)"/g,function(h){return h.toLowerCase()});a=a.replace(/(url\(#highcharts-[0-9]+)&quot;/g,"$1").replace(/&quot;/g,"'");if(a.match(/ xmlns="/g).length===2){a=a.replace(/xmlns="[^"]+"/,"")}return a},exportChart:function(e,b){var c,d=this,a=d.getSVG(U(d.options.exporting.chartOptions,b));e=U(d.options.exporting,e);c=M("form",{method:"post",action:e.url,enctype:"multipart/form-data"},{display:Q},A.body);X(["filename","type","width","svg"],function(f){M("input",{type:I,name:f,value:{filename:e.filename||"chart",type:e.type,width:e.width,svg:a}[f]},null,c)});c.submit();K(c)},print:function(){var g=this,b=g.container,f=[],a=b.parentNode,c=A.body,d=c.childNodes;if(g.isPrinting){return}g.isPrinting=true;var e=A.getElementsByClassName("no-print");X(e,function(h,i){if(h.nodeType===1){f[i]=h.style.display;h.style.display=Q}});V.print();setTimeout(function(){X(e,function(h,i){if(h.nodeType===1){h.style.display=f[i]}});g.isPrinting=false},1000)},contextMenu:function(a,h,l,m,g,i){var j=this,c=j.options.navigation,p=c.menuItemStyle,e=j.chartWidth,n=j.chartHeight,b="cache-"+a,q=j[b],f=P(g,i),r="3px 3px 10px #888",d,k,o;if(!q){j[b]=q=M(N,{className:W+a},{position:C,zIndex:1000,padding:f+T},j.container);d=M(N,null,G({MozBoxShadow:r,WebkitBoxShadow:r,boxShadow:r},c.menuStyle),q);k=function(){O(q,{display:Q})};L(q,"mouseleave",k);X(h,function(t){if(t){var s=M(N,{onmouseover:function(){O(this,c.menuItemHoverStyle)},onmouseout:function(){O(this,p)},innerHTML:t.text||j.options.lang[t.textKey]},G({cursor:"pointer"},p),d);s[F?"ontouchstart":"onclick"]=function(){k();t.onclick.apply(j,arguments)};j.exportDivElements.push(s)}});j.exportDivElements.push(d,q);j.exportMenuWidth=q.offsetWidth;j.exportMenuHeight=q.offsetHeight}o={display:"block"};if(l+j.exportMenuWidth>e){o.right=(e-l-g-f)+T}else{o.left=(l-f)+T}if(m+i+j.exportMenuHeight>n){o.bottom=(n-m-f)+T}else{o.top=(m+i-f)+T}O(q,o)},addButton:function(i){var o=this,p=o.renderer,f=U(o.options.navigation.buttonOptions,i),j=f.onclick,g=f.menuItems,n=f.width,m=f.height,b,d,c,h=f.borderWidth,l={stroke:f.borderColor},k={stroke:f.symbolStroke,fill:f.symbolFill},e=f.symbolSize||12;if(!o.exportDivElements){o.exportDivElements=[];o.exportSVGElements=[]}if(f.enabled===false){return}function a(){d.attr(k);b.attr(l)}b=p.rect(0,0,n,m,f.borderRadius,h).align(f,true).attr(G({fill:f.backgroundColor,"stroke-width":h,zIndex:19},l)).add();c=p.rect(0,0,n,m,0).align(f).attr({id:f._id,fill:"rgba(255, 255, 255, 0.001)",title:o.options.lang[f._titleKey],zIndex:21}).css({cursor:"pointer"}).on("mouseover",function(){d.attr({stroke:f.hoverSymbolStroke,fill:f.hoverSymbolFill});b.attr({stroke:f.hoverBorderColor})}).on("mouseout",a).on("click",a).add();if(g){j=function(){a();var q=c.getBBox();o.contextMenu("export-menu",g,q.x,q.y,n,m)}}c.on("click",function(){j.apply(o,arguments)});d=p.symbol(f.symbol,f.symbolX-(e/2),f.symbolY-(e/2),e,e).align(f,true).attr(G(k,{"stroke-width":f.symbolStrokeWidth||1,zIndex:20})).add();o.exportSVGElements.push(b,c,d)},destroyExport:function(){var b,a=this,c;for(b=0;b<a.exportSVGElements.length;b++){c=a.exportSVGElements[b];c.onclick=c.ontouchstart=null;a.exportSVGElements[b]=c.destroy()}for(b=0;b<a.exportDivElements.length;b++){c=a.exportDivElements[b];J(c,"mouseleave");a.exportDivElements[b]=c.onmouseout=c.onmouseover=c.ontouchstart=c.onclick=null;K(c)}}});function D(b){var a=b.length;while(a--){if(typeof b[a]==="number"){b[a]=Math.round(b[a])-0.5}}return b}Z.Renderer.prototype.symbols.exportIcon=function(c,a,b,d){return D([S,c,a+b,R,c+b,a+d,c+b,a+d*0.8,c,a+d*0.8,"Z",S,c+b*0.5,a+d*0.8,R,c+b*0.8,a+d*0.4,c+b*0.4,a+d*0.4,c+b*0.4,a,c+b*0.6,a,c+b*0.6,a+d*0.4,c+b*0.2,a+d*0.4,"Z"])};Z.Renderer.prototype.symbols.printIcon=function(c,a,b,d){return D([S,c,a+d*0.7,R,c+b,a+d*0.7,c+b,a+d*0.4,c,a+d*0.4,"Z",S,c+b*0.2,a+d*0.4,R,c+b*0.2,a,c+b*0.8,a,c+b*0.8,a+d*0.4,"Z",S,c+b*0.2,a+d*0.7,R,c,a+d,c+b,a+d,c+b*0.8,a+d*0.7,"Z"])};H.prototype.callbacks.push(function(d){var a,b=d.options.exporting,c=b.buttons;if(b.enabled!==false){for(a in c){d.addButton(c[a])}L(d,"destroy",d.destroyExport)}})}());