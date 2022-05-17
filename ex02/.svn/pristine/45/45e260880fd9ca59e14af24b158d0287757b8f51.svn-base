/**
 * 
 */
console.log("Reply Module");
var replyService = (function() {
    var add = function(reply, callback, error) {
        console.log("add()..");
        $.ajax({
            url: "/replies/new",
            type: "post",
            data: JSON.stringify(reply),
			dataType:"json",
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if(callback) callback(result);
            },
            error: function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }

    var getList = function(param, callback, error, before, after) {
        var bno = param.bno;
        var lastRno = param.lastRno || 0;
        var amount = param.amount || 10;

        console.log("getList()..");
        var url = "/replies/pages/" + bno + "/" + lastRno + "/" + amount;

        $.ajax({
            url: url,
            type: "get",
			dataType:"json",
			beforeSend : function() {
				if(before) {
					before();
				}
			},
            success: function(result, status, xhr) {
                if(callback){
                	callback(result);
                }
                if(after) {
                	after(result);
                }
            },
            error: function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }

    var remove = function(reply, callback, error) {
        console.log("remove()..");

        $.ajax({
            url: "/replies/" + reply.rno,
            type: "delete",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if(callback) callback(result);
            },
            error: function(xhr, status, er) {
                if(error) error(xhr);
            }
        })
    }

    function update(reply, callback, error) {
        console.log("update()..");

        $.ajax({
            url: "/replies/" + reply.rno,
            type: "put",
            data: JSON.stringify(reply),
            contentType: "application/json; charset=utf-8",
            success: function(result, status, xhr) {
                if(callback) callback(result);
            },
            error: function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }

    function get(rno, callback, error) {
        console.log("get()..");

        $.ajax({
            url: "/replies/" + rno,
            type: "get",
			dataType:"json",
            success: function(result, status, xhr) {
                if(callback) callback(result);
            },
            error: function(xhr, status, er) {
                if(error) error(er);
            }
        })
    }

    function displayTime(timeValue) {
        console.log("displayTime()..");
        moment.locale('ko');
        return moment(timeValue).fromNow();
    }

    return {add: add, 
        getList: getList, 
        remove: remove,
        get: get,
        update: update,
        displayTime: displayTime
    };
})();
// code runner : Ctrl + Alt + N
