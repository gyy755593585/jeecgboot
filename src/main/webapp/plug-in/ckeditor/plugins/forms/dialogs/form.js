CKEDITOR.dialog.add("form",function(a){var d={action:1,id:1,method:1,enctype:1,target:1};return{title:a.lang.forms.form.title,minWidth:350,minHeight:200,onShow:function(){delete this.form;var b=this.getParentEditor().elementPath().contains("form",1);b&&(this.form=b,this.setupContent(b))},onOk:function(){var f,c=this.form,b=!c;b&&(f=this.getParentEditor(),c=f.document.createElement("form"),!CKEDITOR.env.ie&&c.append(f.document.createElement("br")));b&&f.insertElement(c);this.commitContent(c)},onLoad:function(){function c(e){this.setValue(e.getAttribute(this.id)||"")}function b(f){this.getValue()?f.setAttribute(this.id,this.getValue()):f.removeAttribute(this.id)}this.foreach(function(e){d[e.id]&&(e.setup=c,e.commit=b)})},contents:[{id:"info",label:a.lang.forms.form.title,title:a.lang.forms.form.title,elements:[{id:"txtName",type:"text",label:a.lang.common.name,"default":"",accessKey:"N",setup:function(b){this.setValue(b.data("cke-saved-name")||b.getAttribute("name")||"")},commit:function(b){this.getValue()?b.data("cke-saved-name",this.getValue()):(b.data("cke-saved-name",!1),b.removeAttribute("name"))}},{id:"action",type:"text",label:a.lang.forms.form.action,"default":"",accessKey:"T"},{type:"hbox",widths:["45%","55%"],children:[{id:"id",type:"text",label:a.lang.common.id,"default":"",accessKey:"I"},{id:"enctype",type:"select",label:a.lang.forms.form.encoding,style:"width:100%",accessKey:"E","default":"",items:[[""],["text/plain"],["multipart/form-data"],["application/x-www-form-urlencoded"]]}]},{type:"hbox",widths:["45%","55%"],children:[{id:"target",type:"select",label:a.lang.common.target,style:"width:100%",accessKey:"M","default":"",items:[[a.lang.common.notSet,""],[a.lang.common.targetNew,"_blank"],[a.lang.common.targetTop,"_top"],[a.lang.common.targetSelf,"_self"],[a.lang.common.targetParent,"_parent"]]},{id:"method",type:"select",label:a.lang.forms.form.method,accessKey:"M","default":"GET",items:[["GET","get"],["POST","post"]]}]}]}]}});