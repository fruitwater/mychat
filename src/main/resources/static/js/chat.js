	var login = $('#login')
	var logout = $('#logout');
	var submit = $('#submit');	
	
	//トリガー：参加するボタンをクリックした時
	//処理：ログインフォームに送信する
	login.on('click','.square_btn',function(){
		login.find('form').submit();
	});

	
	//トリガー：ログアウトボタンをクリックした時
	//処理：ログアウトフォームに送信する
	logout.on('click','.square_btn',function(){
		logout.find('form').submit();
	});
	
	
	//トリガー：送信ボタンをクリックした時
	//処理：チャットに書き込む
	submit.on('click','.square_btn',(function(){
		var clickable = clickable === undefined?true:clickable;
		console.log(clickable);
		return function(){
			var message = submit.find('.message').val();
			
			if(!clickable){
				return false;
			}
			
			clickable = false;
			
			$.ajax({
				url:"/chat",
				type:"POST",
				data:JSON.stringify({message:message}),
				contentType: 'application/json',
				datatype: 'json',
				scriptCharset: 'utf-8'
			}).done(function(data, textStatus, jqXHR){
				$('.message').val("");
				var obj = JSON.parse(data);
				if(obj["status"] === 400){
					var messages = obj["messages"];
					for(var i = 0; i < messages.length ;i++){
						var li = $('<li>'+ messages[i] + '</li>');
						$('.error').append(li);
						setTimeout(function(){
							li.fadeOut("slow");
						},3000);
						setTimeout(function(){
							clickable = true;
						},3000);
						return;
					}
				}setTimeout(function(){
					clickable = true;
				},500);
				loadTable(obj);
			}).fail(function(jqXHR, textStatus, errorThrown){
			
			});
			//画面遷移をキャンセルする
			return false;
		}
	})());
	
	
	//トリガー：1秒ごとに
	//処理：チャットをリロードする
	function reload(){
		$.ajax({
			url:"/chat/load",
			type:"GET",
			contentType: 'application/json',
			datatype: 'json',
		}).done(function(data){
			var obj = JSON.parse(data);
			loadTable(obj);
			setTimeout(reload,2000);
		});	
	}	
	setTimeout(reload,2000);
	
	//処理：まだviewにロードされていない書き込みを付け加える
	function loadTable(array){
		var last = $('table').find('tr:last-child').data('id');
		for(var i = 0; i < array.length ;i++){
			var obj = array[i];
			if(obj["id"] <= last){
				continue;			
			}
			$('table').append('<tr data-id="'+ obj["id"] +'"><td>' 
				+ escape(obj["user"]["name"]) + '</td><td>'+ escape(obj["chat"]) 
				+'</td><td class="date">'+ obj["created"] + '</td></tr>');
		}
	}
	
	//処理：エスケープ処理
	var escape = function (str) {
	    return str.replace(/</g, '&lt;').replace(/>/g, '&gt;');
	};