var SecretArticle = React.createClass({
  render: function() {
    return (
			<li class="qf b aml">
			  <a class="qj" href="#"><img class="qh cu" src="img/profile_photo/{article.profileProfileImg}" /></a>
			  <div class="qg">
			    <div class="qn">
			      <small class="eg dp">{article.writtenTime}</small>
			      <h5>{article.profileDisplayName}</h5>
			    </div>
			    <p>{article.content}</p>
			    <div class="any" data-grid="images">
			      <div >
			        <img data-action="zoom" data-width="1050" data-height="700" src="images/bg_1024.jpg" />
			      </div>
			  </div>
			    <ul class="qo alm">
			      <li class="qf">
			        <a class="qj" href="#">
			          <img class="qh cu" src="img/profile_photo/images13.jpeg" />
			        </a>
			        <div class="qg">
			          <strong>Jacon Thornton: </strong>
			          Donec id elit non mi porta gravida at eget metus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Donec ullamcorper nulla non metus auctor fringilla. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Sed posuere consectetur est at lobortis.
			        </div>
			      </li>
			    </ul>
			  </div>
			</li>

    );
  }
});
var articles = [
{
	id:1,
	profileUid:1,
	profileDisplayName: "Dave Gamache",
	profileProfileImg: "images1.jpeg",
	writtenTime: "4 min",
	content: "Aenean lacinia bibendum nulla sed consectetur. Vestibulum id ligula porta felis euismod semper. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.",
	comments: [
		{
      writer: {
			displayName:"Jacon Thornton",
			profileImg:"images13.jpeg"
		},
		comment: "Donec id elit non mi porta gravida at eget metus. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor. Donec ullamcorper nulla non metus auctor fringilla. Praesent commodo cursus magna, vel scelerisque nisl consectetur et. Sed posuere consectetur est at lobortis."
    }
	]
}
];
var article = articles[0];
ReactDOM.render(
  <SecretArticle article={article} />,
  document.getElementById('content')
);
