# coding: utf-8
require 'mysql2'

client = Mysql2::Client.new(:host => 'localhost', :username => 'root', :password => '', :database => 'tekken_combo')

def insert_recipe
  query = "insert into recipes (`author`) values (10)"
  results = client.query(query)
end

def insert_recipe_move
  # 隼 -> 葛2 -> バレリーナ -> おまげ
  ###  query = "insert into recipes_moves"
  client = Mysql2::Client.new(:host => 'localhost', :username => 'root', :password => '', :database => 'tekken_combo')
  move_ids = []
  client.query("select id, name from moves where command like '9LK'").each do |row|
    move_ids.push row["id"]
  end
  client.query("select id, name from moves where command like '4LP,LP'").each do |row|
    move_ids.push row["id"]
  end
  client.query("select id, name from moves where command like '6LK'").each do |row|
    move_ids.push row["id"]
  end
  client.query("select id, name from moves where command like '4RP,LP,WP'").each do |row|
    move_ids.push row["id"]
  end
  client.query("select id, name from moves where command like '金打中に6RP'").each do |row|
    move_ids.push row["id"]
  end
  
  move_ids.each do |id|
    client.query("insert into recipes_moves values(#{id}, 10)")
  end
end

insert_recipe_move
