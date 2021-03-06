package com.example.androiddevchallenge

object PetConstants {
    object Species {
        const val CAT = 0
        const val DOG = 1
        const val ALL = 2
    }
    object Sex {
        const val FEMALE = 0
        const val MALE = 1
        const val ALL = 2
    }
    object SortType {
        const val AGE = "Age"
        const val NAME = "Name"
    }
    object FilterType {
        const val SEX = 0
        const val SPECIES = 1
    }
    val dogHeadImgs = listOf(
        "https://partyanimalsgame.com/static/img/avatars-02@2x.422e7ccd.jpg",
        "https://partyanimalsgame.com/static/img/avatars-05@2x.b4024682.jpg",
        "https://partyanimalsgame.com/static/img/avatars-09@2x.0216e151.jpg"
    )
    val catHeadImgs = listOf(
        "https://partyanimalsgame.com/static/img/avatars-04@2x.54bf35b4.jpg",
        "https://partyanimalsgame.com/static/img/avatars-06@2x.76fa4075.jpg",
    )
    val names = listOf(
        "Aaron",
        "Abel",
        "Abraham",
        "Adam",
        "Adrian",
        "Aidan",
        "Alva",
        "Alex",
        "Alexander",
        "Alan",
        "Albert",
        "Alfred",
        "Andrew",
        "Andy",
        "Angus",
        "Anthony",
        "Apollo",
        "Arnold",
        "Arthur",
        "August",
        "Austin",
        "Ben",
        "Benjamin",
        "Bert",
        "Benson",
        "Bill",
        "Billy",
        "Blake",
        "Bob",
        "Bobby",
        "Brad",
        "Brandon",
        "Brant",
        "Brent",
        "Brian",
        "Brown",
        "Bruce",
        "Caleb",
        "Cameron",
        "Carl",
        "Carlos",
        "Cary",
        "Caspar",
        "Cecil",
        "Charles",
        "Cheney",
        "Chris",
        "Christian",
        "Christopher",
        "Clark",
        "Cliff",
        "Cody",
        "Cole",
        "Colin",
        "Cosmo",
        "Daniel",
        "Denny",
        "Darwin",
        "David",
        "Dennis",
        "Derek",
        "Dick",
        "Donald",
        "Douglas",
        "Duke",
        "Dylan",
        "Eddie",
        "Edgar",
        "Edison",
        "Edmund",
        "Edward",
        "Edwin",
        "Elijah",
        "Elliott",
        "Elvis",
        "Eric",
        "Ethan",
        "Eugene",
        "Evan",
        "Enterprise",
        "Ford",
        "Francis",
        "Frank",
        "Franklin",
        "Fred",
        "Gabriel",
        "Gaby",
        "Garfield",
        "Gary",
        "Gavin",
        "Geoffrey",
        "George",
        "Gino",
        "Glen",
        "Glendon",
        "Hank",
        "Hardy",
        "Harrison",
        "Harry",
        "Hayden",
        "Henry",
        "Hilton",
        "Hugo",
        "Hunk",
        "Howard",
        "Henry",
        "Ian",
        "Ignativs",
        "Ivan",
        "Isaac",
        "Isaiah",
        "Jack",
        "Jackson",
        "Jacob",
        "James",
        "Jason",
        "Jay",
        "Jeffery",
        "Jerome",
        "Jerry",
        "Jesse",
        "Jim",
        "Jimmy",
        "Joe",
        "John",
        "Johnny",
        "Jonathan",
        "Jordan",
        "Jose",
        "Joshua",
        "Justin",
        "Keith",
        "Ken",
        "Kennedy",
        "Kenneth",
        "Kenny",
        "Kevin",
        "Kyle",
        "Lance",
        "Larry",
        "Laurent",
        "Lawrence",
        "Leander",
        "Lee",
        "Leo",
        "Leonard",
        "Leopold",
        "Leslie",
        "Loren",
        "Lori",
        "Lorin",
        "Louis",
        "Luke",
        "Marcus",
        "Marcy",
        "Mark",
        "Marks",
        "Mars",
        "Marshal",
        "Martin",
        "Marvin",
        "Mason",
        "Matthew",
        "Max",
        "Michael",
        "Mickey",
        "Mike",
        "Nathan",
        "Nathaniel",
        "Neil",
        "Nelson",
        "Nicholas",
        "Nick",
        "Noah",
        "Norman",
        "Oliver",
        "Oscar",
        "Owen",
        "Patrick",
        "Paul",
        "Peter",
        "Philip",
        "Phoebe",
        "Quentin",
        "Randall",
        "Randolph",
        "Randy",
        "Ray",
        "Raymond",
        "Reed",
        "Rex",
        "Richard",
        "Richie",
        "Riley",
        "Robert",
        "Robin",
        "Robinson",
        "Rock",
        "Roger",
        "Ronald",
        "Rowan",
        "Roy",
        "Ryan",
        "Sam",
        "Sammy",
        "Samuel",
        "Scott",
        "Sean",
        "Shawn",
        "Sidney",
        "Simon",
        "Solomon",
        "Spark",
        "Spencer",
        "Spike",
        "Stanley",
        "Steve",
        "Steven",
        "Stewart",
        "Stuart",
        "Terence",
        "Terry",
        "Ted",
        "Thomas",
        "Tim",
        "Timothy",
        "Todd",
        "Tommy",
        "Tom",
        "Thomas",
        "Tony",
        "Tyler",
        "Ultraman",
        "Ulysses",
        "Van",
        "Vern",
        "Vernon",
        "Victor",
        "Vincent",
        "Warner",
        "Warren",
        "Wayne",
        "Wesley",
        "William",
        "Willy",
        "Zack",
        "Zachary",
        "Abigail",
        "Abby",
        "Ada",
        "Adelaide",
        "Adeline",
        "Alexandra",
        "Ailsa",
        "Aimee",
        "Alexis",
        "Alice",
        "Alicia",
        "Alina",
        "Allison",
        "Alyssa",
        "Amanda",
        "Amy",
        "Amber",
        "Anastasia",
        "Andrea",
        "Angel",
        "Angela",
        "Angelia",
        "Angelina",
        "Ann",
        "Anna",
        "Anne",
        "Annie",
        "Anita",
        "Ariel",
        "April",
        "Ashley",
        "Audrey",
        "Aviva",
        "Barbara",
        "Barbie",
        "Beata",
        "Beatrice",
        "Becky",
        "Bella",
        "Bess",
        "Bette",
        "Betty",
        "Blanche",
        "Bonnie",
        "Brenda",
        "Brianna",
        "Britney",
        "Brittany",
        "Camille",
        "Candice",
        "Candy",
        "Carina",
        "Carmen",
        "Carol",
        "Caroline",
        "Carry",
        "Carrie",
        "Cassandra",
        "Cassie",
        "Catherine",
        "Cathy",
        "Chelsea",
        "Charlene",
        "Charlotte",
        "Cherry",
        "Cheryl",
        "Chloe",
        "Chris",
        "Christina",
        "Christine",
        "Christy",
        "Cindy",
        "Claire",
        "Claudia",
        "Clement",
        "Cloris",
        "Connie",
        "Constance",
        "Cora",
        "Corrine",
        "Crystal",
        "Daisy",
        "Daphne",
        "Darcy",
        "Dave",
        "Debbie",
        "Deborah",
        "Debra",
        "Demi",
        "Diana",
        "Dolores",
        "Donna",
        "Dora",
        "Doris",
        "Edith",
        "Editha",
        "Elaine",
        "Eleanor",
        "Elizabeth",
        "Ella",
        "Ellen",
        "Ellie",
        "Emerald",
        "Emily",
        "Emma",
        "Enid",
        "Elsa",
        "Erica",
        "Estelle",
        "Esther",
        "Eudora",
        "Eva",
        "Eve",
        "Evelyn",
        "Fannie",
        "Fay",
        "Fiona",
        "Flora",
        "Florence",
        "Frances",
        "Frederica",
        "Frieda",
        "Flta",
        "Gina",
        "Gillian",
        "Gladys",
        "Gloria",
        "Grace",
        "Grace",
        "Greta",
        "Gwendolyn",
        "Hannah",
        "Haley",
        "Hebe",
        "Helena",
        "Hellen",
        "Henna",
        "Heidi",
        "Hillary",
        "Ingrid",
        "Isabella",
        "Ishara",
        "Irene",
        "Iris",
        "Ivy",
        "Jacqueline",
        "Jade",
        "Jamie",
        "Jane",
        "Janet",
        "Jasmine",
        "Jean",
        "Jenna",
        "Jennifer",
        "Jenny",
        "Jessica",
        "Jessie",
        "Jill",
        "Joan",
        "Joanna",
        "Jocelyn",
        "Joliet",
        "Josephine",
        "Josie",
        "Joy",
        "Joyce",
        "Judith",
        "Judy",
        "Julia",
        "Juliana",
        "Julie",
        "June",
        "Karen",
        "Karida",
        "Katherine",
        "Kate",
        "Kathy",
        "Katie",
        "Katrina",
        "Kay",
        "Kayla",
        "Kelly",
        "Kelsey",
        "Kimberly",
        "Kitty",
        "Lareina",
        "Lassie",
        "Laura",
        "Lauren",
        "Lena",
        "Lydia",
        "Lillian",
        "Lily",
        "Linda",
        "lindsay",
        "Lisa",
        "Liz",
        "Lora",
        "Lorraine",
        "Louisa",
        "Louise",
        "Lucia",
        "Lucy",
        "Lucine",
        "Lulu",
        "Lydia",
        "Lynn",
        "Mabel",
        "Madeline",
        "Maggie",
        "Mamie",
        "Manda",
        "Mandy",
        "Margaret",
        "Mariah",
        "Marilyn",
        "Martha",
        "Mavis",
        "Mary",
        "Matilda",
        "Maureen",
        "Mavis",
        "Maxine",
        "May",
        "Mayme",
        "Megan",
        "Melinda",
        "Melissa",
        "Melody",
        "Mercedes",
        "Meredith",
        "Mia",
        "Michelle",
        "Milly",
        "Miranda",
        "Miriam",
        "Miya",
        "Molly",
        "Monica",
        "Morgan",
        "Nancy",
        "Natalie",
        "Natasha",
        "Nicole",
        "Nikita",
        "Nina",
        "Nora",
        "Norma",
        "Nydia",
        "Octavia",
        "Olina",
        "Olivia",
        "Ophelia",
        "Oprah",
        "Pamela",
        "Patricia",
        "Patty",
        "Paula",
        "Pauline",
        "Pearl",
        "Peggy",
        "Philomena",
        "Phoebe",
        "Phyllis",
        "Polly",
        "Priscilla",
        "Quentina",
        "Rachel",
        "Rebecca",
        "Regina",
        "Rita",
        "Rose",
        "Roxanne",
        "Ruth",
        "Sabrina",
        "Sally",
        "Sandra",
        "Samantha",
        "Sami",
        "Sandra",
        "Sandy",
        "Sarah",
        "Savannah",
        "Scarlett",
        "Selma",
        "Selina",
        "Serena",
        "Sharon",
        "Sheila",
        "Shelley",
        "Sherry",
        "Shirley",
        "Sierra",
        "Silvia",
        "Sonia",
        "Sophia",
        "Stacy",
        "Stella",
        "Stephanie",
        "Sue",
        "Sunny",
        "Susan",
        "Tamara",
        "Tammy",
        "Tanya",
        "Tasha",
        "Teresa",
        "Tess",
        "Tiffany",
        "Tina",
        "Tonya",
        "Tracy",
        "Ursula",
        "Vanessa",
        "Venus",
        "Vera",
        "Vicky",
        "Victoria",
        "Violet",
        "Virginia",
        "Vita",
        "Vivian"
    )
    val locations = listOf(
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming"
    )
}