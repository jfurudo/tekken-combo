# -*- coding: utf-8 -*-
require 'open-uri'
require 'nokogiri'
require 'mysql2'

url = 'http://seesaawiki.jp/w/inatekken/d/%b5%c8%b8%f7%b8%c7%cd%ad%b5%bbT7'

html = open(url, "r:euc-jp").read.encode("utf-8")

doc = Nokogiri::HTML.parse(html, nil, 'utf-8')

charactors = []

doc.css('#content_block_3 tr').each do |node|# -*- coding: utf-8 -*-
  puts node
  break
end

client = Mysql2::Client.new(:host => 'localhost', :username => 'root', :password => '', :database => 'tekken_combo')
query = %q{insert into move values("test", "6LK", "", "", "", "", "", "", "")}
results = client.query(query)
puts results
