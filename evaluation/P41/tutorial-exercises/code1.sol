pragma solidity >=0.5.11;

//Write a contract called Person that has an owned reference to a House and a shared reference to a Park.
//The House and Park contracts are given below.
contract Person {
    House house; // house is owned
    Park park; // park is shared
}

contract House {

}

contract Park {

}
