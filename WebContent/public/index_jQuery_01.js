//============================================================
// Register Namespace
//------------------------------------------------------------
var Secret = Secret||{};
Secret.Widget = Secret.Widget||{};

//============================================================
// Constructor - MUST BE AT TOP OF FILE
//------------------------------------------------------------
Secret.Widget.Content = function Secret_Widget_Content(id){

}

Secret.Widget.Content.prototype = {
	render: function(item) {
		var html = '<li class="qf b aml">'
			+ '<a class="qj" href="#"><img class="qh cu" src="img/profile_photo/'+item.profile.profileImg+'"></a>'
			+ '<div class="qg">'

			+ '<div class="qn"><small class="eg dp">'+item.writtenTime+'</small>'
			+ '<h5>'+item.profile.displayName+'</h5></div>'
			+ '<p>'+ item.content+'</p>';

		if(!jQuery.isEmptyObject(item.imageUrl)) {
			html += '<div class="any" data-grid="images">'
				+ '<div style="display: inline-block; margin-bottom: 10px; margin-right: 0px; vertical-align: bottom;">'
				+ '<img data-action="zoom" data-width="1050" data-height="700" src="images/'+item.imageUrl+'" style="width: 347px; height: 232px;"/>'
				+ '</div>'
				+ '</div>';
		}
    $.each( item.comments, function( key, comment ) {

		html += '<ul class="qo alm">'
            + '<li class="qf">'
            + '<a class="qj" href="#"><img class="qh cu" src="img/profile_photo/'+comment.writer.profileImg+'"/></a>'
            + '<div class="qg"><strong>'+comment.writer.displayName+': </strong>'
            +  comment.comment
            + '</div>'
            + '</li>'
            + '</ul>'
    });
    html+=  '</div>'
        + '</li>';
		return html;
	}
}
