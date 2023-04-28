fun find_number(a : Int, list : List<Int>) : Int {
    //println(list)
    if (a < list[list.size / 2])
    	return find_number(a, list.subList(0, list.size / 2))
    else if (a == list[list.size/2])
    	return list.size/2
    else
    	return find_number(a, list.subList(list.size / 2 + 1, list.size)) + list.size / 2 + 1
}

fun main()
{
    val a = listOf(1,2,3,4,5,6,7,8,9,10)
    println(find_number(2, a))
    println(find_number(9, a))
    println(find_number(5, a))
}