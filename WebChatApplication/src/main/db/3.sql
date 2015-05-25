SELECT * 
FROM messages
WHERE user_id = 5 AND date = DATE_FORMAT('2015-05-02','%Y-%m-%e');