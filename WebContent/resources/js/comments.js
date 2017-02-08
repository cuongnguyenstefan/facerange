function onDeleteCommentEvent(data) {
	if (data.status == "success") {
        $('#' + data.source.id.replace( /(:|\.|\[|\]|,|=|@)/g, "\\$1" )).parent().parent().remove();
    }
}