# coding: utf-8
require 'mysql2'

def insert_recipe(client, author_id: 10)
  query = "insert into recipes (`author`) values (#{author_id})"
  results = client.query(query)
end

def insert_recipe_move(client, recipe_id: 1)
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
  
  move_ids.each do |move_id|
    client.query("insert into recipes_moves values(#{recipe_id}, #{move_id})")
  end
end

def main
  client = Mysql2::Client.new(:host => 'localhost',
                              :username => 'root',
                              :password => '',
                              :database => 'tekken_combo')
  
  author_id = 10 # テスト用．user id にしなければならない
  recipe_id = 1
  insert_recipe(client, author_id: author_id)
  insert_recipe_move(client, recipe_id: recipe_id)
end

main
