CKEDITOR.dialog.add("colordialog",function(u){function n(){f.getById(o).removeStyle("background-color");q.getContentElement("picker","selectedColor").setValue("");j&&j.removeAttribute("aria-selected");j=null}function p(a){var a=a.data.getTarget(),b;if("td"==a.getName()&&(b=a.getChild(0).getHtml())){j=a,j.setAttribute("aria-selected",!0),q.getContentElement("picker","selectedColor").setValue(b)}}function y(a){for(var a=a.replace(/^#/,""),b=0,c=[];2>=b;b++){c[b]=parseInt(a.substr(2*b,2),16)}return"#"+(165<=0.2126*c[0]+0.7152*c[1]+0.0722*c[2]?"000":"fff")}function v(a){!a.name&&(a=new CKEDITOR.event(a));var b=!/mouse/.test(a.name),c=a.data.getTarget(),d;if("td"==c.getName()&&(d=c.getChild(0).getHtml())){r(a),b?g=c:w=c,b&&(c.setStyle("border-color",y(d)),c.setStyle("border-style","dotted")),f.getById(k).setStyle("background-color",d),f.getById(l).setHtml(d)}}function r(a){if(a=!/mouse/.test(a.name)&&g){var b=a.getChild(0).getHtml();a.setStyle("border-color",b);a.setStyle("border-style","solid")}!g&&!w&&(f.getById(k).removeStyle("background-color"),f.getById(l).setHtml("&nbsp;"))}function z(d){var e=d.data,a=e.getTarget(),c=e.getKeystroke(),b="rtl"==u.lang.dir;switch(c){case 38:if(d=a.getParent().getPrevious()){d=d.getChild([a.getIndex()]),d.focus()}e.preventDefault();break;case 40:if(d=a.getParent().getNext()){(d=d.getChild([a.getIndex()]))&&1==d.type&&d.focus()}e.preventDefault();break;case 32:case 13:p(d);e.preventDefault();break;case b?37:39:if(d=a.getNext()){1==d.type&&(d.focus(),e.preventDefault(!0))}else{if(d=a.getParent().getNext()){if((d=d.getChild([0]))&&1==d.type){d.focus(),e.preventDefault(!0)}}}break;case b?39:37:if(d=a.getPrevious()){d.focus(),e.preventDefault(!0)}else{if(d=a.getParent().getPrevious()){d=d.getLast(),d.focus(),e.preventDefault(!0)}}}}var t=CKEDITOR.dom.element,f=CKEDITOR.document,h=u.lang.colordialog,q,x={type:"html",html:"&nbsp;"},j,g,w,m=function(a){return CKEDITOR.tools.getNextId()+"_"+a},k=m("hicolor"),l=m("hicolortext"),o=m("selhicolor"),i;(function(){function d(A,B){for(var s=A;s<A+3;s++){var C=new t(i.$.insertRow(-1));C.setAttribute("role","row");for(var D=B;D<B+3;D++){for(var E=0;6>E;E++){e(C.$,"#"+a[D]+a[E]+a[s])}}}}function e(B,s){var C=new t(B.insertCell(-1));C.setAttribute("class","ColorCell");C.setAttribute("tabIndex",-1);C.setAttribute("role","gridcell");C.on("keydown",z);C.on("click",p);C.on("focus",v);C.on("blur",r);C.setStyle("background-color",s);C.setStyle("border","1px solid "+s);C.setStyle("width","14px");C.setStyle("height","14px");var A=m("color_table_cell");C.setAttribute("aria-labelledby",A);C.append(CKEDITOR.dom.element.createFromHtml('<span id="'+A+'" class="cke_voice_label">'+s+"</span>",CKEDITOR.document))}i=CKEDITOR.dom.element.createFromHtml('<table tabIndex="-1" aria-label="'+h.options+'" role="grid" style="border-collapse:separate;" cellspacing="0"><caption class="cke_voice_label">'+h.options+'</caption><tbody role="presentation"></tbody></table>');i.on("mouseover",v);i.on("mouseout",r);var a="00 33 66 99 cc ff".split(" ");d(0,0);d(3,0);d(0,3);d(3,3);var c=new t(i.$.insertRow(-1));c.setAttribute("role","row");for(var b=0;6>b;b++){e(c.$,"#"+a[b]+a[b]+a[b])}for(b=0;12>b;b++){e(c.$,"#000000")}})();return{title:h.title,minWidth:360,minHeight:220,onLoad:function(){q=this},onHide:function(){n();var a=g.getChild(0).getHtml();g.setStyle("border-color",a);g.setStyle("border-style","solid");f.getById(k).removeStyle("background-color");f.getById(l).setHtml("&nbsp;");g=null},contents:[{id:"picker",label:h.title,accessKey:"I",elements:[{type:"hbox",padding:0,widths:["70%","10%","30%"],children:[{type:"html",html:"<div></div>",onLoad:function(){CKEDITOR.document.getById(this.domId).append(i)},focus:function(){(g||this.getElement().getElementsByTag("td").getItem(0)).focus()}},x,{type:"vbox",padding:0,widths:["70%","5%","25%"],children:[{type:"html",html:"<span>"+h.highlight+'</span>\t\t\t\t\t\t\t\t\t\t\t\t<div id="'+k+'" style="border: 1px solid; height: 74px; width: 74px;"></div>\t\t\t\t\t\t\t\t\t\t\t\t<div id="'+l+'">&nbsp;</div><span>'+h.selected+'</span>\t\t\t\t\t\t\t\t\t\t\t\t<div id="'+o+'" style="border: 1px solid; height: 20px; width: 74px;"></div>'},{type:"text",label:h.selected,labelStyle:"display:none",id:"selectedColor",style:"width: 74px",onChange:function(){try{f.getById(o).setStyle("background-color",this.getValue())}catch(a){n()}}},x,{type:"button",id:"clear",style:"margin-top: 5px",label:h.clear,onClick:n}]}]}]}]}});