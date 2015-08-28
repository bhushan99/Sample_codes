import sys 
import requests 
import lxml.html 
import warnings
import time
import sets
from collections import Counter

def geturl(abc):
	while(1):
		try:
			page = lxml.html.document_fromstring(requests.get(abc).content)
			return page
		except:
			time.sleep(.1)
			continue

def gettext(lst1):
	lst=[]
	for i in range(len(lst1)):
		try:
			fg=lst1[i].encode('ascii')
			lst.append(fg)
		except:
			pass
	lst=filter(lambda a: a != ' ', lst)
	st=' '.join(lst)
	st=st.replace('\r','')
	st=st.replace('\n','')
	return st

def main():

	i=0 
	ul=[]
	dic={}

	# crawl new car reviews
	# there are 6 webpages containing reviews
	while i<6:
		page=geturl("http://www.team-bhp.com/forum/official-new-car-reviews/index"+str(i+1)+".html?sort=dateline&order=desc&daysprune=-1")
		i+=1

		id= page.xpath('//a[@id]/@id')
		
		for j in id:
			if j.startswith("thread_title"):
				
				# go to individual car review page
				revl = page.xpath('//a[@id="'+j+'"]/@href')[0]			
				xcv = geturl(revl)

				# get total number of pages for that car
				pg=int(xcv.xpath('//td[@class="vbmenu_control" and @style="font-weight:normal"]')[0].text.split(' ')[-1])
				rl=revl[:-5]

				# crawl reviews on each page
				for pag in range(pg):
					pg1=pag+1
					revl=rl+'-'+str(pg1)+'.html'    # url for page
					ids=xcv.xpath('//div[@id]/@id')
					for gh in ids:

						if gh.startswith('post_message'):
							# get each post (review)
							revll = xcv.xpath('//div[@id="'+gh+'"]/text()')
							gh=gh.replace('post_message','postmenu')

							# get person who posted review
							usr=xcv.xpath('//div[@id="'+gh+'"]/a[@class="bigusername"]/text()')
							gh=gh.replace('postmenu','td_post')
							
							try:
								# get date and time when review was posted
								tds=xcv.xpath('//td[@id="'+gh+'"]/div[@class="smallfont"]')[-1]
								xt = tds[-1].iterchildren()
								xt.__init__()
								xs= xt.next()
							except:
								pass
							
							try:
								#increment count for user who posted review
								ul.append(usr[0])
								if usr[0] in dic.keys():
									dic[usr[0]]=dic[usr[0]]+1
								else:
									dic[usr[0]]=1
							except:
								pass

	# print count of posts by each user
	for i in dic.keys():
		print i,dic[i]

if __name__ == '__main__':
    main()
