/* Author:	Shannon Foss
 * File:	FizzFile.groovy
 * Desc:	Extensible version of FizzBuzz that reads in fizz buzz values 
 * 			from a file then prints the values to a file
 * Demos:	Variables, Console in/out, File IO, Maps, Methods, Closures.
 */

	// define files
	def outfile = new File ("output.txt")
	def infile = new File ("input.txt")
	
	//read input file and place into map
	def map = [ : ]
	infile.eachLine { line -> 
		def pair = line.tokenize() 
		map.put(pair[0].toInteger(), pair[1])
	}
	
	//read in user's input to define fizzbuzz limit
	def limit
	System.in.withReader {
		print "Enter FizzBuzz Limit: "
		limit = it.readLine()
		limit = limit.toInteger()
	}
	def list = 1..limit
	
	//write fizzbuzz values to file
	if(outfile.exists()){
		outfile.delete()
	}
	def printWriter = outfile.newPrintWriter()
	list.each {x -> printWriter.println fizz(map, x)}
	
	//close file
	printWriter.flush()
	printWriter.close()

	/* Method: 		String fizz ( Map map, int num )
	 * Description: Given a map and a value, this method will return the value string 
	 * 				if num is divisible by any keys of the map, otherwise it will 
	 * 				return num.
	 * Parameters:	Map map : Takes in a map of the fizzbuzz values in the format of 
	 * 				[int key : String val]  (ex.  [ 3 : "Fizz" ] )
	 * 				int num : The value that is being tested
	 * Return:		Returns a String result
	 */
	def fizz (map, num){
		def result = ""
		map.each {k, v ->
			if(num % k == 0){
				result += v
			}
		}
		if(result.empty){
			result+=num.toString()
		}
		return result
	}
	
/* Output
 * 	Enter FizzBuzz Limit: 15
 * 
 * 	1
 * 	2
 * 	Fizz
 * 	4
 * 	Buzz
 * 	Fizz
 * 	Bazz
 * 	8
 * 	Fizz
 * 	Buzz
 * 	11
 * 	Fizz
 * 	13
 * 	Bazz
 *	FizzBuzz
 */