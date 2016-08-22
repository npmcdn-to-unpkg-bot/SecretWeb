var secretApp = angular.module('secretApp', []);


secretApp.controller('ArticleListController', function ArticleListController($rootScope, $scope, $http) {

  $rootScope.$on('updatedArticle', function(event, args) {
	  $http.get('/rest/article').success(function(result) {
	    var articles = result.data;
	    $scope.articles = articles;
	  });
  });
  
  $http.get('/rest/article').success(function(result) {
    var articles = result.data;
    $scope.articles = articles;
  });
});


secretApp.controller('OwnerProfileController', function OwnerProfileController($scope, $http) {
  $http.get('/rest/profile/owner').success(function(result) {
    var profile = result.data;
    $scope.owner = profile;
  });
});

secretApp.controller('InBoxController', function InBoxController($scope, $http) {
  $http.get('/rest/inbox').success(function(result) {
 
    $scope.items = result.data;
  });
});

secretApp.controller('ArticleSubmitController',  function($rootScope, $scope, $http) {
	$scope.article = {};
	$scope.submit = function() {
	    if ($scope.article.content) {
	    	//alert("Submitted:" + $scope.article.msg);
			// Posting data to php file
			$http.post('/rest/article', $scope.article)
			  .success(function(data) {
			    if (data.errorMsg) {
			      $scope.errorMsg = data.errorMsg;
			    } else {
				  $scope.article.content = "";
			      $rootScope.$broadcast('updatedArticle', {});
			    }

			  });
	    } else {
	    	alert( "empty" );
	    }
	};
});
