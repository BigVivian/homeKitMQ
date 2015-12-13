#!/usr/bin/env python
#encoding=utf-8

import string
import random
import sys
import time


def init():
	f = open('data1000.csv',"w")
	for i in range(1000):
		row = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n" % (i,randomString(20),i,randomString(2000),randomDigit(randomLen()),randomDigit(randomLen()),randomDigit(randomLen()),randomDigit(randomLen()),randomString(randomLen(20)),randomString(randomLen(20)),randomString(20),randomString(20),randomString(20),randomDigit(),randomDigit(),randomString(20),int(time.time()*1000),randomString(randomLen(15)),randomDigit(randomLen(8)))
		f.write(row)
	f.close()
	# f = open('data10000.csv',"w")
	# for i in range(10000):
	# 	row = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n" % (i,randomString(20),i,randomString(2000),randomDigit(8),randomDigit(10),randomDigit(2),randomDigit(2),randomString(2),randomString(2),randomString(2),randomString(2),randomString(2),randomDigit(2),randomDigit(2),randomString(12),int(time.time()*1000),randomString(1000),randomDigit(4))
	# 	f.write(row)
	# f.close()
	# f = open('data100000.csv',"w")
	# for i in range(100000):
	# 	row = "%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n" % (i,randomString(20),i,randomString(200),randomDigit(8),randomDigit(10),randomDigit(2),randomDigit(2),randomString(2),randomString(2),randomString(2),randomString(2),randomString(2),randomDigit(2),randomDigit(2),randomString(12),int(time.time()*1000),randomString(1000),randomDigit(4))
	# 	f.write(row)
	# f.close()

def randomString(size=6,chars=string.ascii_uppercase+string.digits):
	return ''.join(random.choice(chars) for _ in range(size))

def randomDigit(size=3,chars=string.digits):
	return ''.join(random.choice(chars) for _ in range(size))

def randomLen(max=9):
	return random.choice(range(max))+1	

if __name__ == '__main__':
	init()



