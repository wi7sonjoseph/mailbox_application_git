var mailBox = {
		loggedInUserId: null,
		inboxTable: null,
		sentTable: null,
		init: function(){
			mailBox.loadUserDetails();
			$("#composeLink").click(mailBox.loadComposeTab);
			$("#sendMessage").click(mailBox.sendMessage);
			$("#inboxLink").click(mailBox.goToInbox);
			$("#goToInbox").click(function(){$("#inboxLink").click();});
			$("#backToInbox").click(function(){$("#inboxLink").click();});
			$("#sentLink").click(mailBox.goToSent);
		},
		checkIsNullOrUndefinedOrEmpty: function(arg){
			var errorStatus = false;
			if (arg == undefined || arg == null || arg == ""){
				errorStatus = true;
			}
			return errorStatus;
		},
		loadUserDetails: function(){
				$.ajax({
					type: "GET",
					url: "user",
					success: function(jsonObject){
						if (!mailBox.checkIsNullOrUndefinedOrEmpty(jsonObject) && !mailBox.checkIsNullOrUndefinedOrEmpty(jsonObject.lstUserTO) && jsonObject.lstUserTO.length > 0){
							mailBox.loggedInUserId = jsonObject.lstUserTO[0].id;
							$("#inboxLink").click();
						}
					},
					errorObject: function(errorObject){
						
					}
				});
		},
		loadComposeTab: function(){
			$("#subjectMail").val("");
			$("#messageMail").val("");
			mailBox.loadMailTo();
		},
		loadMailTo:function(){
		      $("#toUser").select2({
		         ajax: {
		            url: "user",
		            dataType: 'json',
		            delay: 250,
		            data: function(params) {
		               return {
		                  name: params.term, // search term
		                 // limit: params.page
		               };
		            },
		            processResults: function(data, params) {
		               params.page = params.page || 1;
		               var users = data.lstUserTO;
		               var i = 0;
		               while(i < users.length){
		                  var txt = users[i]['userName'];
						  users[i]["text"] = txt;
		                  i++;   
		               }
		               return {results: users};
		            },
		            initSelection: function(element, callback) { },
		            cache: true
		         },
		         placeholder: "Search User",
		         escapeMarkup: function(markup) {return markup;}, // let our custom formatter work
		         minimumInputLength: 1,
		      });
		      
		      $(".select2-container").css("width", "100%");
		      $("#toUser").val([]).trigger('change');
		   },
		   formatRepoSelection : function(repo){
		      return repo.text;
		   },
		   sendMessage: function(){
			   var errorFlag = false;
			   var errorMessage = "";
			   var firstError = false;
			   if ($("#toUser").val() == null){
					 errorFlag = true;
					 errorMessage += "Select atleast one User to send.";
					 firstError = true;
			   }
			   var secondError = false;
			   if ($("#subjectMail").val() == null || $("#subjectMail").val().trim() == ""){
					 errorFlag = true;
					 if (firstError){
						 errorMessage += "<br>";
					 }
					 errorMessage += "Subject is mandatory";
					 secondError = true;
			   }
			  
			   if ($("#messageMail").val() == null || $("#messageMail").val().trim() == ""){
					 errorFlag = true;
					 if (secondError){
						 errorMessage += "<br>";
					 }
					 errorMessage += "Message is mandatory";
			   }
				if (errorFlag){
					toastr.error(errorMessage, "Message");
					return;
				}
			   var lstUserMessage = [];
			   var toList = $("#toUser").val();
			   for (var i =0; i<toList.length; i++){
				   var userMessageTO = {
						   receiverUserId: toList[i],
						   senderUserId: mailBox.loggedInUserId
				   };
				   lstUserMessage.push(userMessageTO);
			   }
			   var messageJSON = {
					   mailBody: $("#messageMail").val(),
					   subject: $("#subjectMail").val(),
					   lstUserMessageTO: lstUserMessage
			   }
			   
			   $.ajax({
				  type : "POST",
				  url: "message",
				  data : JSON.stringify(messageJSON),
				  headers : {
						'Content-Type' : 'application/json',
				  },
				  dataType : 'json',
				  success: function(jsonObject){
					  toastr.success("Message sent successfully", "Message");
					  $("#inboxLink").click();
				  },
				  error: function(errorObject){},
				  complete: function(){
					  toastr.success("Message sent successfully", "Message");
					  $("#inboxLink").click();
				  }
			   });
		   },
		   goToInbox: function() {
			   $("#inboxTableDiv").removeClass("hide");
			   $("#viewInboxMessageDiv").addClass("hide");
			   var height = $(window).height() - 170;
			   mailBox.inboxTable = $("#tblUserInbox").DataTable({		
			        "rowCallback": function(nRow, aData, iDisplayIndex) {
			        		console.log(aData);
			        		if (aData.readStatus != null && aData.readStatus == "Y"){
			        			$('td', nRow).css("background", "#fff");
			        		}else{
			        			$('td', nRow).css("background", "#c6d8ea");
			        		}
			                
			        },
			        'order': [[1, 'asc']],
					"scrollY" :height,
					"scrollX": false,
					"pageLength": -1,
			        "destroy": true,
					"dom": "<'row' <'col-md-12'>><'row'<'col-md-6 col-md-12'><'col-md-6 col-md-12'>><'table-scrollable't><'row'<'col-md-5 col-md-12'><'col-md-7 col-md-12'>>", 
					"aoColumns": [
			        {"mData": "fromName"},
			        {"mData": "subject"},
			        {"mData": "mailBody"},
					],
					responsive: false,
					"aaSorting": [ [ 0, "asc" ] ],
					"sAjaxSource": "user/"+mailBox.loggedInUserId+"?screenFlag=I",				
					"sAjaxDataProp": "inboxMessages",
					"oLanguage": {
				        "sEmptyTable": "No message(s) in Inbox"
				    },
					"fnServerData": function (sSource, aoData, fnCallback, oSettings ) {
						oSettings.jqXHR = $.ajax( {
							"url": sSource, 
							"data": aoData, 
							"success": function(data, textStatus, jqXHR) {
			               fnCallback(data, textStatus, jqXHR);
							}
						});
					},
					"fnInitComplete": function(oSettings, json) {
					    setTimeout(function(){
					    	$("#fromInboxHeading").click();
					    },200); 
						mailBox.inboxTable.columns.adjust();
					}
				});	
			   
			   $('#tblUserInbox tbody').off().on( 'click', 'tr', function () {
				     $("#inboxTableDiv").addClass("hide");
				     $("#viewInboxMessageDiv").removeClass("hide");
				     var iPos = $('#tblUserInbox').dataTable().fnGetPosition(this);
				     var aData = $('#tblUserInbox').dataTable().fnGetData( iPos );
				     mailBox.loadInboxViewMessageDiv(aData);
			   }); 
		   },
		   loadInboxViewMessageDiv: function(jsonObject){
			   if (jsonObject != undefined && jsonObject != null){
				   if (jsonObject.fromName != undefined && jsonObject.fromName != null){
					   $("#labelInboxFrom").html(jsonObject.fromName);
				   }
				   if (jsonObject.subject != undefined && jsonObject.subject != null){
					   $("#labelInboxSubject").html(jsonObject.subject);
				   }
				   if (jsonObject.mailBody != undefined && jsonObject.mailBody != null){
					   $("#labelInboxMessage").text(jsonObject.mailBody);
				   }
				   mailBox.updateMessage(jsonObject.id);
			   }
		   },
		   updateMessage: function(messageId){
			   var messageJSON = {
					   id: messageId
			   }
			   
			   $.ajax({
				  type : "PUT",
				  url: "message",
				  data : JSON.stringify(messageJSON),
				  headers : {
						'Content-Type' : 'application/json',
				  },
				  dataType : 'json',
				  success: function(jsonObject){},
				  error: function(errorObject){}
			   });
		   },
		   goToSent: function(){
			   var height = $(window).height() - 170;
			   mailBox.sentTable = $("#tblUserSent").DataTable({		
			        "rowCallback": function(nRow, aData, iDisplayIndex) {
			                $('td', nRow).eq(4).addClass("unRead");
			        },
			        'order': [[1, 'asc']],
					"scrollY" :height,
					"scrollX": false,
					"pageLength": -1,
			        "destroy": true,
					"dom": "<'row' <'col-md-12'>><'row'<'col-md-6 col-md-12'><'col-md-6 col-md-12'>><'table-scrollable't><'row'<'col-md-5 col-md-12'><'col-md-7 col-md-12'>>", 
					"aoColumns": [
			        {"mData": "toName"},
			        {"mData": "subject"},
			        {"mData": "mailBody"},
					],
					responsive: false,
					"aaSorting": [ [ 0, "asc" ] ],
					"sAjaxSource": "user/"+mailBox.loggedInUserId+"?screenFlag=S",				
					"sAjaxDataProp": "sentMessages",
					"oLanguage": {
				        "sEmptyTable": "No message(s) in Sent"
				    },
					"fnServerData": function (sSource, aoData, fnCallback, oSettings ) {
						oSettings.jqXHR = $.ajax( {
							"url": sSource, 
							"data": aoData, 
							"success": function(data, textStatus, jqXHR) {
			               fnCallback(data, textStatus, jqXHR);
							}
						});
					},
					"fnInitComplete": function(oSettings, json) {
					    setTimeout(function(){
					    	$("#fromSentHeading").click();
					    },200); 
						mailBox.sentTable.columns.adjust();
					}
				});	
		   }
}
mailBox.init();

//toastr options 
toastr.options = {
  "closeButton": true,
  "debug": false,
  "newestOnTop": false,
  "progressBar": false,
  "positionClass": "toast-top-center",
  "preventDuplicates": true,
  "onclick": null,
  "showDuration": "300",
  "hideDuration": "1000",
  "timeOut": "4000",
  "extendedTimeOut": "1000",
  "showEasing": "swing",
  "hideEasing": "linear",
  "showMethod": "fadeIn",
  "hideMethod": "fadeOut"
}