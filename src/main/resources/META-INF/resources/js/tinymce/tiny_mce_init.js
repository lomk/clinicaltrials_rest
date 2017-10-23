tinyMCE.init({
    selector: '#mytextarea',
    plugins: 'image, table',
    toolbar: "table, image",
    file_browser_callback: function(field_name, url, type, win) {
        if(type=='image') $('#my_form input').click();
    }
});