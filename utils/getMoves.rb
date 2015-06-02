# -*- coding: utf-8 -*-
require 'open-uri'
require 'nokogiri'
require 'mysql2'

url = 'http://seesaawiki.jp/w/inatekken/d/%b5%c8%b8%f7%b8%c7%cd%ad%b5%bbT7'

html = open(url, "r:euc-jp").read.encode("utf-8")

doc = Nokogiri::HTML.parse(html, nil, 'utf-8')

charactors = []

client = Mysql2::Client.new(:host => 'localhost', :username => 'root', :password => '', :database => 'tekken_combo')

c = 0
data = []
doc.css('#content_block_3 tr').each do |node|# -*- coding: utf-8 -*-
  data[c] = []
  node.css('td').each do |td|
    puts td.text
    data[c].push '"' + td.text + '"'
  end
  if data[c].length != 0
    query = "insert into moves values(#{c}, #{data[c].join(',')} )"
    puts query
    results = client.query(query)
  end
  c = c + 1
end

