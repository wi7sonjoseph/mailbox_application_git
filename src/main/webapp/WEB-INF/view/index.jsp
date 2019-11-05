<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
 <head>
        <meta charset="utf-8" />
        <title>MailBox Application</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/select2/css/select2-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
      	
      </head>
<body>

<style>
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 90%}
    
    /* Set gray background color and 100% height */
    .sidenav {
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    .rowTopPadding{
    	padding-top:2%;
    }
    
    .select2-container{
    	width: 100%;
    }
    #inboxGridContainer .dataTables_scrollHeadInner{
	  width:100% !important;
	}
	#inboxGridContainer .dataTables_scrollHeadInner table{
	  width:100% !important;
	}
	.headerView{
		font-size: 16px;
	}
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height: auto;} 
    }
  </style>
</head>
<body>

<div class="container-fluid">
  <div class="row content">
    <div class="col-sm-3 sidenav">
      <h2>MailBox Application</h2>
		<ul class="nav nav-pills nav-stacked">
		  <li class="active"><a data-toggle="pill" id="composeLink" href="#home">Compose</a></li>
		  <li><a data-toggle="pill" id="inboxLink"  href="#menu1">Inbox</a></li>
		  <li><a data-toggle="pill"  id="sentLink"  href="#menu2">Sent</a></li>
		</ul>
    </div>

    <div class="col-sm-9">
		<div class="tab-content">
		  <div id="home" class="tab-pane fade in active">
			<h3>Compose</h3>
			<div class="row">
				<div class="col-md-12 rowTopPadding">
					<div class="form-group">
						<label class="control-label col-md-1">To</span></label>
						<div class="col-md-11">
							<select required="true" class="form-control" id="toUser" name="toUser" placeholder="Search User" multiple></select>
						</div>
					</div>
				</div>
				<div class="col-md-12 rowTopPadding">
					<div class="form-group">
						<label class="control-label col-md-1">Subject</label>
						<div class="col-md-11">
							<input type="text" required="true" class="form-control"  placeholder="Type your Subject here" id="subjectMail" name="subjectMail" />
						</div>
					</div>
				</div>
				<div class="col-md-12 rowTopPadding">
					<div class="form-group">
						<div class="col-md-12">
							<textarea required="true" rows="15" class="form-control" id="messageMail" name="messageMail" placeholder="Type your Message here" /></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-12 rowTopPadding">
                	<button type="button" id="sendMessage" class="btn green" style="background:#c6c6e2;" >Send</button>
					<button type="button" id="goToInbox" class="btn blue pull-right" style="background:#c3e49e;">Inbox</button>
				</div>
			</div>
		  </div>
		  <div id="menu1" class="tab-pane fade">
		  	<div id="inboxTableDiv">
				<h3>Inbox</h3>
				<div class="row">
					<div class="col-md-12">
						<div class="portlet box blue-madison no-margin-bottom">
							<div class="portlet-body">
								<div id="inboxGridContainer">
					               <table class="table table-bordered table-hover cell-border table-striped" id="tblUserInbox">
					                  <thead>
					                     <tr>
					                        <th id="fromInboxHeading">From</th>
					                        <th >Subject</th>
					                        <th >Message</th>
					                     </tr>
					                  </thead>
					                  <tbody style="border-right: 1px solid #AAA;border-left: 1px solid #AAA;"> </tbody>
					               </table>
					            </div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="viewInboxMessageDiv" class="hide">
				<div class="row headerView " style="padding-top: 10px;">
					<div class="col-md-1 bold text-right">From: </div>
					<div class="col-md-11 " id="labelInboxFrom"></div>
				</div>
				<div class="row headerView " style="padding-top: 10px;">
					<div class="col-md-1 bold text-right">Subject: </div>
					<div class="col-md-11 " id="labelInboxSubject"></div>
				</div>
				<div class="row headerView " style="padding-top: 10px;">
					<div class="col-md-1 bold text-right">Message: </div>
					<div class="col-md-11 " id="labelInboxMessage"></div>
				</div>
				<div class="row" style="padding-top: 10px;padding-right:10px;">
					<button type="button" id="backToInbox" class="btn blue pull-right" style="background:#c3e49e;">Back</button>
				</div>	
			</div>	
		  </div>
		  <div id="menu2" class="tab-pane fade">
			<h3>Sent</h3>
			<div class="row">
				<div class="col-md-12">
					<div class="portlet box blue-madison no-margin-bottom">
						<div class="portlet-body">
							<div id="sentGridContainer">
				               <table class="table table-bordered table-hover cell-border table-striped" id="tblUserSent">
				                  <thead>
				                     <tr>
				                        <th  id="fromSentHeading">To</th>
				                        <th >Subject</th>
				                        <th >Message</th>
				                     </tr>
				                  </thead>
				                  <tbody style="border-right: 1px solid #AAA;border-left: 1px solid #AAA;"> </tbody>
				               </table>
				            </div>
						</div>
					</div>
				</div>
			</div>
		  </div>
		</div>
    </div>
  </div>
</div>

<footer class="container-fluid">
  <p>For Demo purpose!!</p>
</footer>

</body>
		<script src="${pageContext.request.contextPath}/assets/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/select2/js/select2.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/js/mailboxUtils.js" type="text/javascript"></script>
</html>