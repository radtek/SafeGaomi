
	var $searchableTree = $('#treeview-searchable').treeview({
          data: buildDomTree(),
		  onNodeSelected: function(event, node) {
              $('#selectable-output-select').html('DOM ID:' + node.id + ' DOM:' + node.text);
			  $('#input-search').attr("value",node.text);
			  $("#treeview").hide();
            }
    });
  
    var search = function(e) {
		  $("#treeview").show();
          var pattern = $('#input-search').val();
          var options = {
            ignoreCase: true,
            revealResults: true,
			showTags: true,
			selectable: false,
			highlightSearchResults: true,
			levels: 0
          };
  
    var results = $searchableTree.treeview('search', [ pattern, options ]);

    var output = "<ul class='select2-results'><li class='select2-no-results'>" + results.length + ' matches found</li>';
        $.each(results, function (index, result) {
            output += "<li class='list-group-item list-group-item node-treeview-searchable'>" + result.text + "</li>";
          });
		  output += "</ul>";
          $('#search-output').html(output);
        }

		$('#input-search').on('keyup', search);
        //$('#btn-search').on('click', search);
        
       //$('#btn-clear-search').on('click', function (e) {
         // $searchableTree.treeview('clearSearch');
         // $('#input-search').val('');
         // $('#search-output').html('');
        //});
		

		$("#input-search").focus( function () {
			
			$("#treeview").show(); 
		} );
		
	
		
		
    function buildDomTree() {
	        var data = [
			{text:"北京市",id:"1",
			  nodes:[{text:"东城区",id:"12"},{text:"西城区",id:"13"},{text:"丰台区",id:"14",
			      nodes:[{text:'花乡派出所',id:"121"}]
			  }]
			}
			];
	        return data;
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		