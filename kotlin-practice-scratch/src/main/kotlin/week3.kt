fun main() {

    class BankAccount(val number: String, val name : String, _balance : Double) {

        var balance : Double = _balance
            private set(value) {
                field = value
            }

        fun deposit(depositMoney : Double) {
            balance += depositMoney
        }


        fun withdraw(withdrawMoney : Double) : Boolean {
            if (balance >= withdrawMoney) {
                balance -= withdrawMoney
                return true
            }
            else return false;
        }

    }

    println("문제1")
    val account = BankAccount("1234", "John Smith", 100.0)
    println(account.balance) // Output: 100.0

    account.deposit(50.0)
    println(account.balance) // Output: 150.0

    val success = account.withdraw(75.0)
    println(success) // Output: true
    println(account.balance) // Output: 75.0

    val failure = account.withdraw(100.0)
    println(failure) // Output: false
    println(account.balance) // Output: 75.0


    println()
    println("문제2")
    open class Vehicle(var make: String, var model: String, var year: Int) {

        open fun start() { }

        open fun stop() { }
    }

    class Car(_make: String, _model: String, _year: Int, var numDoors: Int) : Vehicle(_make, _model, _year) {
        override fun start() { println("Car 동작 시작") }
        override fun stop() { println("Car 동작 정지") }
    }

    class  Motorcycle(
        _make: String, _model: String, _year: Int, var hasSidecar
        : Boolean) : Vehicle(_make, _model, _year) {
        override fun start() { println("Motorcycle 동작 시작") }
        override fun stop() { println("Motorcycle 동작 정지") }
    }

    class Bicycle(_make: String, _model: String, _year: Int, var numGears: Int) : Vehicle(_make, _model, _year) {
        override fun start() { println("Bicycle 동작 시작") }
        override fun stop() { println("Bicycle 동작 정지") }
    }

    val car = Car("Honda", "Civic", 2022, 4)
    val motorcycle = Motorcycle("Harley Davidson", "Fat Boy", 2022, true)
    val bicycle = Bicycle("Trek", "FX 3", 2022, 24)

    car.start()
    car.stop()

    motorcycle.start()
    motorcycle.stop()

    bicycle.start()
    bicycle.stop()


    println()
    println("문제3")

    class Vector (val x : Int, val y : Int) {

        operator fun plus(other : Vector) : Vector
                = Vector(x + other.x, y + other.y)

        override fun toString() : String = "(${x}, ${y})"
    }

    val v1 = Vector(1, 2)
    val v2 = Vector(3, 4)

    println(v1 + v2) // Output: (4, 6)


}