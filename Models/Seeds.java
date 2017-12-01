package ie.freetime.reddwarf.Models;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



public class Seeds {

    public static ArrayList<QuestionsModel> easyQuestionsArray, mediumQuestionsArray, hardQuestionsArray;
    public static ArrayList<String> quotesArray;

    static DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    
    public static void populateEasyArray(){

        easyQuestionsArray = new ArrayList<>();

        Query getEasyQuestions = myDatabase.child("addedQuestions").child("easy");
        getEasyQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot thisQuestion : dataSnapshot.getChildren()){
                        QuestionsModel question = thisQuestion.getValue(QuestionsModel.class);
                        easyQuestionsArray.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        QuestionsModel questionsModel1 = new QuestionsModel("easy001",
                "What catastrophe occured while Lister was being held in stasis?",
                "Rimmer became an officer", "Flooding on the lower decks shrunk Cat's suits",
                "A radiation leak killed the crew", "Kochanski rekindled her romance with Tim",
                "Rimmer 'accidentally' snapped the neck on Lister's guitar", "A radiation leak killed the crew");

        QuestionsModel questionsModel2 = new QuestionsModel("easy002",
                "Which cast member from the original UK show also played their character in the un-aired episode of Red Dwarf USA?",
                "Craig Charles", "Chris Barrie", "Danny John-Jules", "Robert Llewellyn",
                "Norman Lovett", "Robert Llewellyn");

        QuestionsModel questionsModel3 = new QuestionsModel("easy003",
                "What's Rimmers middle name?", "Ace", "James", "Judas",
                "John", "Ironballs","Judas");

        QuestionsModel questionsModel4 = new QuestionsModel("easy004",
                "On which ship was Kryten discovered by the boys from the Dwarf?",
                "SSS Esperanto", "The Simulant Battle Cruiser", "Gemini 12",
                "Wildfire", "Nova 5", "Nova 5");

        QuestionsModel questionsModel5 = new QuestionsModel("easy005",
                "In the episode 'Gunmen of the Apocalypse', what game character did Cat assume?",
                "Dangerous Dan McGrew", "The Riviera Kid", "Queeg", "Duanne Dibbley",
                "The Yuppie Kid", "The Riviera Kid");

        QuestionsModel questionsModel6 = new QuestionsModel("easy006",
                "Who was Lister speaking to when he said this?\n\n" +
                        "\"No offence, but get real man. Most eunuchs have got more balls than you.\"",
                "Cat", "Holly", "Kryten","Rimmer","Ace","Rimmer");

        QuestionsModel questionsModel7 = new QuestionsModel("easy007","Who was Rimmer speaking to when he said this?\n\n" +
                "\"With this little baby running at full pelt, I confidently predict we could have a full fruit salad by the end of the year.\"",
                "Holly","A Vending Machine","Lister","Cat","Kochanski","Lister");

        QuestionsModel questionsModel8 = new QuestionsModel("easy008",
                "Who said this?\n\n" +
                        "\"Ah that's awful man. When a woman screws you up so bad you want to become a squirrel.\"",
                "Rimmer","Lister","Cat","Holly","Petersen","Cat");

        QuestionsModel questionsModel9 = new QuestionsModel("easy009",
                "Who said this?\n\n" +
                        "\"Aliens!! They're probably going to return Glen Miller\"",
                "Cat","Lister","Rimmer","Holly","Kryten","Rimmer");

        QuestionsModel questionsModel10 = new QuestionsModel("easy010",
                "Who said this?\n" +
                        "\"If he offers to show you his photo collection, my advice is decline politely.\"",
                "Lister","Rimmer","Cat","Holly","Hilly","Lister");

        QuestionsModel questionsModel11 = new QuestionsModel("easy011",
                "What rank does Rimmer hold aboard Red Dwarf?",
                "Officer","First Technician","Second Technician","Third Technician","Chicken Soup Machine Repairman", "Second Technician");

        QuestionsModel questionsModel12 = new QuestionsModel("easy012",
                "Who did Rimmer appoint as his Seargent in the episode 'Meltdown'?",
                "Kryten","Elvis","Winnie the Pooh","Mother Teresa","Albert Einstein","Elvis");

        QuestionsModel questionsModel13 = new QuestionsModel("easy013",
                "Lister had a dream to own a farm, where?",
                "Fiji", "Mount Fuji","Io","Swindon","Inside Better Than Life", "Fiji");

        QuestionsModel questionsModel14 = new QuestionsModel("easy14",
                "When Red Dwarf is stolen the boys are forced to travel in which ship?",
                "Starbug", "Starbeetle", "Blue Midget","White Giant","Nova 5","Starbug");

        QuestionsModel questionsModel15 = new QuestionsModel("easy15",
                "The simulant that reaches the end of time and returns to judge the worth of peoples lives is known as?",
                "The Inquisitor","The Simulant Captain", "The Simulant Convict", "Crawford","Hogey the Roguey","The Inquisitor");

        QuestionsModel questionsModel16 = new QuestionsModel("easy16",
                "Which of these forms have we NOT seen a polymorph take?",
                "Listers underpants","Fluffy white rabbit","Mrs. Rimmer","Tennis ball","Holly","Holly");

        QuestionsModel questionsModel17 = new QuestionsModel("easy17",
                "Which of these was not a regular cast member?",
                "Chris Barrie","Craig Charles","Danny John-Jules","Grant Naylor","Robert Llewellyn","Grant Naylor");


        QuestionsModel questionsModel18 = new QuestionsModel("easy18",
                "Who said it?\n\n'The accident involving me, the toaster, the waste disposal and a 14-pound lump hammer.'",
                "Rimmer","Lister","Kryten","Cat","Holly","Lister");


        QuestionsModel questionsModel19 = new QuestionsModel("easy19",
                "In the episode 'Marooned' what did Lister NOT burn?",
                "His guitar","Rimmers camphor wood chest","Bundle of money","Books","Wooden toy soldiers","His guitar");

        QuestionsModel questionsModel20 = new QuestionsModel("easy20",
                "Who said it?\n\n'Well, I'm sorry! I've just had a rather nasty experience. I've just seen someone I know die in the most " +
                        "hideous, hideous way!'",
                "Rimmer","Lister","Holly","Cat","Kryten","Rimmer");

        QuestionsModel questionsModel21 = new QuestionsModel("easy21",
                "Who said it?\n\n'As my father always said, \"Shiny clean boots and a spanking short haircut, and you can cope with anything.\"" +
                        "He said that just before that rather unfortunate suicide business.'",
                "Rimmer","Lister","Holly","Cat","Kryten","Rimmer");

        QuestionsModel questionsModel22 = new QuestionsModel("easy22",
                "Who said it?\n\n'You know, I wish I was someone else. Then I could kiss me.'",
                "Rimmer","Lister","Holly","Cat","Kryten","Cat");

        QuestionsModel questionsModel23 = new QuestionsModel("easy23",
                "Who said it?\n\n'Even better. Switch me on, switch me off, like I'm some battery powered sex aid.' ",
                "Rimmer","Lister","Holly","Cat","Kryten","Rimmer");

        QuestionsModel questionsModel24 = new QuestionsModel("easy24",
                "Who said it?\n\n'So you wanna fight, doth you? Let's have a go then, don't think just because I'm a man of peace I won't punch" +
                        " your teeth out!'",
                "Jesus","Lister","Ghandi","Einstein","Kryten","Jesus");

        QuestionsModel questionsModel25 = new QuestionsModel("easy25",
                "Who said it?\n\n'I'll handle this. Americans love an English accent, so that rules out Lister.'",
                "Rimmer","Kochanski","Holly","Cat","Kryten","Rimmer");

        QuestionsModel questionsModel26 = new QuestionsModel("easy26",
                "Who said it?\n\n'And you can swing from trees, right? With your super swingy monkey arms, right?'",
                "Rimmer","Lister","Holly","Cat","Kryten","Cat");

        QuestionsModel questionsModel27 = new QuestionsModel("easy27",
                "'Oh! There's a folder in here marked Captain Bollocks. Could that be you?' Who is Captain Bollocks?",
                "Rimmer","Lister","Holly","Cat","Kryten","Rimmer");

        QuestionsModel questionsModel28 = new QuestionsModel("easy28",
                "What's missing?\n\nPlease, sir, I am now fluent in all the deceptive arts. I could work for ______.'",
                "FIFA","The simulants","CNN","Fox News","The FA","FIFA");

        QuestionsModel questionsModel29 = new QuestionsModel("easy29",
                "Who was it said to?\n\n'And Officer Rimmer once said: \"If you want to feel my boot up your recharge socket, keep talking!\"'",
                "Rimmer","Lister","Holly","Cat","Kryten","Kryten");

        QuestionsModel questionsModel30 = new QuestionsModel("easy30",
                "Who said it?\n\n'Good grief! Vandals have stolen the droids entire hard drive, and stuffed envelopes through" +
                        " its voice unit! What kind of place is this?'",
                "Rimmer","Lister","Holly","Cat","Kryten","Kryten");

        QuestionsModel questionsModel31 = new QuestionsModel("easy31",
                "Who said it?\n\n'I'm so glad I'm not him! The guy's a wreck! And pretending to be someone else all day, that's no" +
                        " way to make a living. Smeghead!'",
                "Rimmer","Lister","Holly","Cat","Kryten","Lister");

        QuestionsModel questionsModel32 = new QuestionsModel("easy32",
                "'I have a date with Patricia Carling from supplies on Saturday night, she says my _____ are my " +
                        "best feature, if I go like this I'M ONLY HALF LOVELY!'\n\nWhat was Warden Ackerman missing?",
                "An eye","A testicle","An ear","A buttock","A leg","An eye");

        QuestionsModel questionsModel33 = new QuestionsModel("easy33",
                "What happened to Birdman's pet sparrow Pete?",
                "Cat ate him","Lister sat on him","Kryten accidentally reverses his evolution, reverting to a dinousaur",
                "Birdman lets him go free on one of the rarely visited decks", "He beats Rimmer at a game of Risk and is ejected into space as" +
                " retribution","Kryten accidentally reverses his evolution, reverting to a dinousaur");

        QuestionsModel questionsModel34 = new QuestionsModel("easy34",
                "Who was Big Meat speaking to?\n\n'There ain't no one more bad-assed, evil than me in the whole of hell. What makes you think you can diss me and live?'",
                "Cat","Rimmer","Lister","Kryten","Kochanski","Cat");

        QuestionsModel questionsModel35 = new QuestionsModel("easy35",
                "In how many episodes (S01-11) does Ace Rimmer make an appearance?",
                "2","3","4","5","6","3");

        QuestionsModel questionsModel36 = new QuestionsModel("easy36",
                "'Now, this 3-dimensional sculpture in particular is quite exquisite. Its simplicity, " +
                        "its bold, stark lines. Pray, what do you call it?' \n\nWhat was Legions response?",
                "The light switch","The mirror","The wall","The door","The food dispenser","The light switch");

        QuestionsModel questionsModel37 = new QuestionsModel("easy37",
                "In what episode did Kryten say this?\n\n'I'm some kind of robot, who's fighting this virus, and none of this exists. It's all in a fever. " +
                        "Except for you guys, who really do exist, only you're not really here, you're really " +
                        "in some spaceship in the future. Hell, if that's got to make sense, I don't want to be sober.'",
                "Gunmen of the Apocalypse","Legion","Tikka to Ride","Psirens","Out of Time", "Gunmen of the Apocalypse");

        QuestionsModel questionsModel38 = new QuestionsModel("easy38",
                "Who said this?\n\n'Thermos, sandwiches, corn plasters, telephone money, dandruff brush, animal footprint chart and...one " +
                        "triple thick condom. You never know.'",
                "Duane Dibbley","Lister","Kryten","Ace Rimmer","George MacIntyre","Duane Dibbley");

        QuestionsModel questionsModel39 = new QuestionsModel("easy39",
                "Who was Cat talking to?\n\n'I have got hair like yours, just not on my head.'",
                "Lister","Rimmer","Ace","Hogey the Roguey","Captain Hollister","Rimmer");

        QuestionsModel questionsModel40 = new QuestionsModel("easy40",
                "Which episode is this from?\n\n'Lister to Red Dwarf. The intruder seems blissfully unaware that we have a fairly " +
                        "sturdy holowhip in the munitions cabinet.'",
                "Holoship","The Inquisitor","Terrorform","Dimension Jump","Demons and Angels","Holoship");

        QuestionsModel questionsModel41 = new QuestionsModel("easy41",
                "Who was Kryten speaking to?\n\n'Excuse me. Could I possibly distract you for just a brief second?'",
                "The Inquisitor","Queeg","A polymorph","A GELF","Caligua","The Inquisitor");

        QuestionsModel questionsModel42 = new QuestionsModel("easy42",
                "Which episode is this from?\n\n'Only as a myth. A dark fable. A horror tale. Told across the embers of a flickering midnight fire " +
                        "where hardened space dogs gather to drink from fermented vegetable products " +
                        "and compete in tales of blood-chilling terror.'",
                "Holoship","The Inquisitor","Terrorform","Dimension Jump","Demons and Angels","The Inquisitor");

        QuestionsModel questionsModel43 = new QuestionsModel("easy43",
                "In 'Quarantine', what was the name of crazy Rimmer's hand puppet?",
                "Mr. Flibble","Mr. Fibble","Mr. Flubber","Mr. Flubble","Mr. Flibbler", "Mr. Flibble");

        QuestionsModel questionsModel44 = new QuestionsModel("easy44",
                "Where did Lister taste his first ever edible Pot Noodle?",
                "In Better Than Life","In an alternate dimension","On board the High Red Dwarf ship",
                "Marooned on an ice planet with Rimmer",
                "He never has","On board the High Red Dwarf ship");

        QuestionsModel questionsModel45 = new QuestionsModel("easy45",
                "After finally having learned the art of deception, what does Kryten call a 'small off-duty " +
                        "Czechoslovakian traffic warden'?",
                "Banana","Apple","Orange","Pear","Lemon","Banana");

        QuestionsModel questionsModel46 = new QuestionsModel("easy46",
                "When Cat first appears to the rest of the crew from where does he enter?",
                "Air vent","Xpress lift","Wardrobe in Lister's quarters","Stasis chamber","Cinema","Air vent");

        QuestionsModel questionsModel47 = new QuestionsModel("easy47",
                "What was the name of the shuttle used by the crew in series I and II?",
                "Starbug","Blue Midget","White Midget","White Giant","Wildfire","Blue Midget");

        QuestionsModel questionsModel48 = new QuestionsModel("easy48",
                "Finish Kryten\'s quote: \n\n'Spin my nipple nuts and ___________'",
                "Send me to Alaska","Blow out my groinal attachment","Swap out my head","Disable my etiquette protocols",
                "Call me Harry","Send me to Alaska");

        QuestionsModel questionsModel49 = new QuestionsModel("easy49",
                "When the crew meet up after getting ready for the female crew of the Nova 5 what is Cat wearing?",
                "A gold space suit","A silver space suit","A multicoloured 3-piece suit","A purple space suit with sequins",
                "An officers uniform","A gold space suit");

        QuestionsModel questionsModel50 = new QuestionsModel("easy50",
                "In the theme song what type of fish would the singer like to be nibbling on her toes?",
                "Goldfish","Piranha","Guppy","Haddock","Catfish","Goldfish");

        QuestionsModel questionsModel51 = new QuestionsModel("easy51",
                "What kind of ship is Red Dwarf?",
                "Cargo Ship","Mining Ship","Military Ship","Exploratory Ship","Transport Ship","Mining Ship");

        QuestionsModel questionsModel52 = new QuestionsModel("easy52",
                "In 'Backwards' what language do Lister and Cat think the locals are speaking?",
                "English","French","German","Spanish","Bulgarian","Bulgarian");

        QuestionsModel questionsModel53 = new QuestionsModel("easy53",
                "What is Kryten's favourite TV show?",
                "Androids","The Brittas Empire","Robot Wars","Zero G Match of the Day","Scrapheap Challenge","Androids");

        QuestionsModel questionsModel54 = new QuestionsModel("easy54",
                "In 'Queeg' what game does Holly lose at to Queeg?",
                "Tiddlywinks","Snakes & Ladders","Chess","Monopoly","Risk","Chess");

        QuestionsModel questionsModel55 = new QuestionsModel("easy55",
                "In 'Confidence & Paranoia' what does Lister's feverish dream cause to happen in the sleeping quarters?",
                "It rains fish and the Mayor of Warsaw appears and spontaneously combusts","Another Rimmer appears",
                "Rimmer gets beehive hair and breasts","Talkie Toaster comes back to life","Petersen appears",
                "It rains fish and the Mayor of Warsaw appears and spontaneously combusts");

        QuestionsModel questionsModel56 = new QuestionsModel("easy56",
                "In 'Better Than Life' who does Cat take as a mate?",
                "Marilyn Monroe","Marilyn Manson","Audrey Hepburn","Sigourney Weaver","Jessica Rabbit","Marilyn Monroe");

        QuestionsModel questionsModel57 = new QuestionsModel("easy57",
                "Rimmer's one and only lover was Yvonne McGruder, what was she?",
                "A blow up doll","A female model mechanoid","A psiren","A boxing champion","A hallucination","A boxing champion");

        QuestionsModel questionsModel58 = new QuestionsModel("easy58",
                "Who was it that changed Rimmer from a soft-light to hard-light hologram?",
                "Legion","Lister","Kryten","Jesus","Ace","Legion");

        QuestionsModel questionsModel59 = new QuestionsModel("easy59",
                "In 'Timeslides' we find out Lister had a rock band when he was a teenager, what was the band's name?",
                "Smeg and the Heads","Dave and the Dented Veg Tins","Smeg and the Keks","Dave and the Om's","Smeg and the Shadows","Smeg and the Heads");

        QuestionsModel questionsModel60 = new QuestionsModel("easy60",
                "What was the name of Lister's cat, the ancestor of Cat?",
                "Frankenstein","Dracula","Freddie","Beetlejuice","Mrs. Flibble","Frankenstein");

        QuestionsModel questionsModel61 = new QuestionsModel("easy61",
                "According to Queeg, which book does Holly rely on to navigate space?",
                "The Junior Colour Encyclopedia of Space", "The Hitch-Hikers Guide to the Galaxy","First Big Book of Space",
                "I Want to be an Astronaut","Me and My Place in Space", "The Junior Colour Encyclopedia of Space");

        QuestionsModel questionsModel62 = new QuestionsModel("easy62",
                "The contents of Petes stomach escape from many places, scarring many Red Dwarf security members for life. Which of these had Pete NOT consumed?",
                "2.5 tonnes of ice-cream","400 crates of orange ice-pops","2000 gallons of Coca-Cola","Cow vindaloo","10000 space weevils","10000 space weevils");

        QuestionsModel questionsModel63 = new QuestionsModel("easy63",
                "Who said this to Lister?\n\n" +
                        "\"Not necessarily. If I'd known it meant that much to you, that you needed to see me naked so badly, I wouldn't necessarily have said no.\"",
                "Rimmer","Cat","Holly","Kochanski","Kryten","Kochanski");

        QuestionsModel questionsModel64 = new QuestionsModel("easy64",
                "Who said it?\n\n" +
                        "\"To make up for it, and to indicate how truly sorry I am, here's two bags of self-raising flour. Something I didn't need " +
                        "any help with yesterday.\"",
                "Rimmer","Lister","Cat","Holly","Kochanski","Rimmer");

        QuestionsModel questionsModel65 = new QuestionsModel("easy65",
                "Who said it?\n\n" +
                        "\"Your type isn't Kochanski, Listy. It's someone called 'Tiffany'. It's someone who drinks Campari and soda and " +
                        "wears orange crotchless panties...\"",
                "Rimmer","Cat","Todhunter","Tim","Ace","Rimmer");


        easyQuestionsArray.add(questionsModel1);
        easyQuestionsArray.add(questionsModel2);
        easyQuestionsArray.add(questionsModel3);
        easyQuestionsArray.add(questionsModel4);
        easyQuestionsArray.add(questionsModel5);
        easyQuestionsArray.add(questionsModel6);
        easyQuestionsArray.add(questionsModel7);
        easyQuestionsArray.add(questionsModel8);
        easyQuestionsArray.add(questionsModel9);
        easyQuestionsArray.add(questionsModel10);
        easyQuestionsArray.add(questionsModel11);
        easyQuestionsArray.add(questionsModel12);
        easyQuestionsArray.add(questionsModel13);
        easyQuestionsArray.add(questionsModel14);
        easyQuestionsArray.add(questionsModel15);
        easyQuestionsArray.add(questionsModel16);
        easyQuestionsArray.add(questionsModel17);
        easyQuestionsArray.add(questionsModel18);
        easyQuestionsArray.add(questionsModel19);
        easyQuestionsArray.add(questionsModel20);
        easyQuestionsArray.add(questionsModel21);
        easyQuestionsArray.add(questionsModel22);
        easyQuestionsArray.add(questionsModel23);
        easyQuestionsArray.add(questionsModel24);
        easyQuestionsArray.add(questionsModel25);
        easyQuestionsArray.add(questionsModel26);
        easyQuestionsArray.add(questionsModel27);
        easyQuestionsArray.add(questionsModel28);
        easyQuestionsArray.add(questionsModel29);
        easyQuestionsArray.add(questionsModel30);
        easyQuestionsArray.add(questionsModel31);
        easyQuestionsArray.add(questionsModel32);
        easyQuestionsArray.add(questionsModel33);
        easyQuestionsArray.add(questionsModel34);
        easyQuestionsArray.add(questionsModel35);
        easyQuestionsArray.add(questionsModel36);
        easyQuestionsArray.add(questionsModel37);
        easyQuestionsArray.add(questionsModel38);
        easyQuestionsArray.add(questionsModel39);
        easyQuestionsArray.add(questionsModel40);
        easyQuestionsArray.add(questionsModel41);
        easyQuestionsArray.add(questionsModel42);
        easyQuestionsArray.add(questionsModel43);
        easyQuestionsArray.add(questionsModel44);
        easyQuestionsArray.add(questionsModel45);
        easyQuestionsArray.add(questionsModel46);
        easyQuestionsArray.add(questionsModel47);
        easyQuestionsArray.add(questionsModel48);
        easyQuestionsArray.add(questionsModel49);
        easyQuestionsArray.add(questionsModel50);
        easyQuestionsArray.add(questionsModel51);
        easyQuestionsArray.add(questionsModel52);
        easyQuestionsArray.add(questionsModel53);
        easyQuestionsArray.add(questionsModel54);
        easyQuestionsArray.add(questionsModel55);
        easyQuestionsArray.add(questionsModel56);
        easyQuestionsArray.add(questionsModel57);
        easyQuestionsArray.add(questionsModel58);
        easyQuestionsArray.add(questionsModel59);
        easyQuestionsArray.add(questionsModel60);
        easyQuestionsArray.add(questionsModel60);
        easyQuestionsArray.add(questionsModel61);
        easyQuestionsArray.add(questionsModel62);
        easyQuestionsArray.add(questionsModel63);
        easyQuestionsArray.add(questionsModel64);
        easyQuestionsArray.add(questionsModel65);

    }

    public static void populateMediumArray(){

        mediumQuestionsArray = new ArrayList<>();

        Query getMediumQuestions = myDatabase.child("addedQuestions").child("medium");
        getMediumQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot thisQuestion : dataSnapshot.getChildren()){
                        QuestionsModel question = thisQuestion.getValue(QuestionsModel.class);
                        mediumQuestionsArray.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        QuestionsModel questionsModel1 = new QuestionsModel("medium001",
                "In what year did Red Dwarf first air on BBC 2?", "1985","1987",
                "1988","1990", "1991","1988");

        QuestionsModel questionsModel2 = new QuestionsModel("medium002",
                "Which one of Rimmers characteristics does NOT appear on a gravestone in 'Terrorform'?",
                "Self Respect", "Generosity", "Self Confidence", "Honour", "Courage", "Courage");

        QuestionsModel questionsModel3 = new QuestionsModel("medium003",
                "What is the full title applicable to GELF's?",
                "Generally Entertaining & Lively Fellows", "Genetically Engineered Life Form",
                "Genetically Engineered Life Force","Greatly Educated Lethal Force",
                "Genetically Engineered Lower Form", "Genetically Engineered Life Form");

        QuestionsModel questionsModel4 = new QuestionsModel("medium004",
                "Which two actresses played the role of Kristine Z. Kochanski?",
                "Claire Grogan and Chloe Annett", "Chloe Annett and Hattie Hayridge",
                "Hattie Hayridge and Terry Farrell", "Claire Grogan and Jane Leeves",
                "Terry Farrell and Jane Leeves", "Claire Grogan and Chloe Annett");

        QuestionsModel questionsModel5 = new QuestionsModel("medium005",
                "In what year and how high in the UK charts did Danny John-Jules place with the single 'Tongue Tied'?",
                "1991, placed 11-20", "1992, placed 1-10", "1992, placed 11-20", "1993, placed 1-10", "1993, placed 11-20",
                "1993, placed 11-20");

        QuestionsModel questionsModel6 = new QuestionsModel("medium006",
                "What part was Craig Charles originally offered?",
                "Rimmer","Cat","Lister","Holly","Petersen","Cat");

        QuestionsModel questionsModel7 = new QuestionsModel("medium007",
                "Who said this?\n" +
                        "'Such a jerky middle name. Why didn't they just call me Cecil and have done with it?'",
                "Kryten","Lister","Cat","Rimmer","Holly","Kryten");

        QuestionsModel questionsModel8 = new QuestionsModel("medium008",
                "When Rimmer takes the identity of Ace how does he fumble the catchphrase?",
                "Stoke me a kipper, I'll be back for breakfast","Stoke me a clipper, I'll be back for Christmas",
                "Stroke me a clipper, I'll be back for biscuits","Smoke me a clipper, I'll be back for breakfast",
                "Smoke me a kipper, I'll be back for Christmas","Stoke me a clipper, I'll be back for Christmas");

        QuestionsModel questionsModel9 = new QuestionsModel("medium009",
                "'Filthy piece of distended rectum.' Who did Rimmer direct this insult at?",
                "Rimmer 2", "Lister", "Kryten", "Cat", "Captain Hollister", "Rimmer 2");

        QuestionsModel questionsModel10 = new QuestionsModel("medium10",
                "What rank did George MacIntyre hold on Red Dwarf?",
                "First Technician","Second Technician","Third Technician","Flight Coordinator","First Officer","Flight Coordinator");

        QuestionsModel questionsModel11 = new QuestionsModel("medium11",
                "How many years imprisonment was Rimmer sentenced to in the episode 'Justice'?",
                "9328", "999","5000","8759","9000","9328");

        QuestionsModel questionsModel12 = new QuestionsModel("medium12",
                "What name did Listers father give his dog?",
                "Bexley","Jim","Hannah","McCartney","Lennon","Hannah");

        QuestionsModel questionsModel13 = new QuestionsModel("medium13",
                "Upon finishing the Red Dwarf game who discovered they were Sebastian Doyle?",
                "Lister","Cat","Kryten","Holly","Rimmer","Lister");

        QuestionsModel questionsModel14 = new QuestionsModel("medium14",
                "What were Rimmer's last words before being killed by the radiation leak?",
                "Gazpacho soup","Smeg","Mummy","Yvonne","I could have been great","Gazpacho soup");

        QuestionsModel questionsModel15 = new QuestionsModel("medium15",
                "In the episode 'The End' what reason does Lister give for service robots not having to clean the chicken soup dispensers?",
                "They aren't programmed to do it","Religious grounds","They had a fight over the existence of silicon heaven",
                "They've got a better union","They can't reach the nozzles","They've got a better union");

        QuestionsModel questionsModel16 = new QuestionsModel("medium16",
                "You traded away the essence of your genetic make-up for $100 ad half a packet of fags?' Who said it?",
                "Rimmer","Lister","Holly","Cat","Kryten","Kryten");

        QuestionsModel questionsModel17 = new QuestionsModel("medium17",
                "'I had a mid-life crisis once...I had all the symptoms. First, I denied it, then I got angry," +
                        " started thinking about missed opportunities, then I got depressed, then finally I accepted it.' How long did Lister's" +
                        " crisis last?",
                "5 minutes","30 minutes","5 hours","5 weeks","5 months","5 minutes");

        QuestionsModel questionsModel18 = new QuestionsModel("medium18",
                "Who was it said to?\n\n'It wasn't a cat, wasn't a woman, you just had sex with a genetically engineered insectoid arachnid.'",
                "Rimmer","Lister","Holly","Cat","Kryten","Cat");

        QuestionsModel questionsModel19 = new QuestionsModel("medium19",
                "Who said it?\n\n'This thingy whatever it is -  it's messing with the supply thingy pipe stuff thing, isn't it?'",
                "Rimmer","Lister","Holly","Cat","Kryten","Lister");

        QuestionsModel questionsModel20 = new QuestionsModel("medium20",
                "Who said it?\n\n'That damn coffee machine. I'm gonna bust his ass down to tampon dispenser.'",
                "Rimmer","Lister","Kochanski","Captain Hollister","Kryten","Captain Hollister");

        QuestionsModel questionsModel21 = new QuestionsModel("medium21",
                "Who said it?\n\n'We don't know where we are, what to do, and have no clue what's happening. Things are back to normal.'",
                "Rimmer","Lister","Kochanski","Captain Hollister","Kryten","Kochanski");

        QuestionsModel questionsModel22 = new QuestionsModel("medium22",
                "Who said it?\n\n'Six nipples? I wonder what the female of the species is like.'",
                "Rimmer","Lister","Kochanski","Captain Hollister","Kryten","Captain Hollister");

        QuestionsModel questionsModel23 = new QuestionsModel("medium23",
                "What was Holly\'s plan?\n\n\"I've devoted all my runtime to looking for a loophole in the Prison Regs. And I" +
                        "think I've come up with something which means you can serve your entire 2 year sentence in just 14 weeks.\"",
                "Become a dog", "Join the Canaries","Hide contraband on Rimmer and tell the guards", "Sleep 18 hours per day",
                "Build a time drive capable of short jumps through time after each charge", "Become a dog");

        QuestionsModel questionsModel24  = new QuestionsModel("medium24",
                "Who was the first actor to play Kryten?",
                "Robert Llewellyn","David Ross","Norman Lovett","Don Warrington","Jack Docherty","David Ross");

        QuestionsModel questionsModel25 = new QuestionsModel("medium25",
                "Who said it?\n\n'Time is a great healer. Unless you've got a rash, then you're better off with some ointment.'",
                "Rimmer","Lister","Holly","Captain Hollister","Kryten","Holly");

        QuestionsModel questionsModel26 = new QuestionsModel("medium26",
                "What news was Kryten doing a poor job of giving Lister?\n\n'I'm so traumatised that no-one's had the guts to tell you the horrible, terrible, terrible, hideously " +
                        "appalling news. I don't think I can even speak now.'",
                "Kochanski was dating Tim again","Vindaloo supply exhausted","Lager supply exhausted",
                "Rimmer had activated Rimmer 2","Rimmer had passed his navigation exam","Kochanski was dating Tim again");

        QuestionsModel questionsModel27 = new QuestionsModel("medium27",
                "'Oh my goodness, we've been frozen in time again! Curious, it must be a warp in the space-time continuum. " +
                        "How curious it isn't affecting me.'\n\nWhat had Kryten been talking about to make everyone freeze?",
                "Showering with the female prisoners","The unlikelihood of surviving the upcoming Canaries mission",
                "The time he took a double polaroid","Fixing the puncture on Rimmers blow-up doll", "The medical procedures carried out on Lister without his knowledge",
                "Showering with the female prisoners");

        QuestionsModel questionsModel28 = new QuestionsModel("medium28",
                "Who was Cat speaking to?\n\n'You're gonna squeeze their rolls? Man, that's irritating! But hey, in many ways, they'll be quite relieved!'",
                "Rimmer","Baxter","Kill Crazy","Big Meat","Warden Ackerman","Baxter");

        QuestionsModel questionsModel29 = new QuestionsModel("medium29",
                "Who said it?\n\n'Causality? Well, OK, you know, one event causes another, OK, but sometimes, you just " +
                        "gotta say, the laws of time and space? Who gives a smeg?'",
                "Kryten","Rimmer","Ace","Lister","Holly","Kryten");

        QuestionsModel questionsModel30 = new QuestionsModel("medium30",
                "In which episode does Ace say this?\n\n'Princess Bonjella, Ace Rimmer. There'll be time for explanations later. And hopefully, " +
                        "some sex.'",
                "Stoke Me A Clipper", "Dimension Jump","Emohawk: Polymorph II","Rimmerworld","Epideme","Stoke Me A Clipper");

        QuestionsModel questionsModel31 = new QuestionsModel("medium31",
                "In which episode does Ace Rimmer first make an appearance? What a guy...",
                "Dimension Jump","Stoke Me A Clipper","Emohawk: Polymorph II", "Rimmerworld","Epideme","Dimension Jump");


        QuestionsModel questionsModel32 = new QuestionsModel("medium32",
                "Who said this?\n\n'And here's the kicker, all the interesting things that ever happened to me happened when " +
                        "I was in the room! Coincidence? Get outta here...'",
                "Cat","Lister","Confidence","Ace","Hogey the Roguey","Cat");

        QuestionsModel questionsModel33 = new QuestionsModel("medium33",
                "Finish Rimmers quote...\n\n 'Open communication channels, Lister. Broadcast on all known " +
                        "frequencies and in all known languages, including ______.'",
                "Welsh","Irish","Scottish","Scouse","French","Welsh");

        QuestionsModel questionsModel34 = new QuestionsModel("medium34",
                "For what empire was Tarka Dal an ambassador?",
                "The Great Vindalooian Empire","The Great Tikka Masalaan Empire","The Great Lagerooian Empire",
                "The Great Bedford Falls Empire","The Great Dwarvian Empire","The Great Vindalooian Empire");

        QuestionsModel questionsModel35 = new QuestionsModel("medium35",
                "Who said this?\n\n'Dear Lord, what has created such foulness? Is this the product of a marriage twixt woman and gerbil?'",
                "Rimmer","Rimmer clone from 'Rimmerworld'","The Inquisitor","Low Rimmer from 'Demons and Angels'","Low Cat from 'Demons and Angels'",
                "Rimmer clone from 'Rimmerworld'");

        QuestionsModel questionsModel36 = new QuestionsModel("medium36",
                "Who said this?\n\n'Enough of this heresy. At the stroke of dawn take them out and kill them. And when you've " +
                        "killed them burn the bodies, and bring me the cold ashes on a silver plate with " +
                        "a glass of chilled sancerre.'",
                "Rimmer clone from 'Rimmerworld'","Caligula from 'Meltdown'","Hitler from 'Meltdown'",
                "Rasputin from 'Meltdown'","Low Rimmer from 'Demons and Angels'","Rimmer clone from 'Rimmerworld'");

        QuestionsModel questionsModel37 = new QuestionsModel("medium37",
                "What crew member was this said to?\n\n'You make love like a Japanese meal; small portions, but oh, so many courses.'",
                "Lister","Rimmer","Cat","Holly","Kryten", "Rimmer");

        QuestionsModel questionsModel38 = new QuestionsModel("medium38",
                "In what episode did Cat say this?\n\n'I have given pleasure to the world because I have such a beautiful ass.'",
                "Holoship","Duct Soup","Timeslides","The Inquisitor","Justice","The Inquisitor");

        QuestionsModel questionsModel39 = new QuestionsModel("medium39",
                "In the episode 'The Inquisitor', who are the two crew members judged worthy of not being erased?",
                "Rimmer and Cat", "Rimmer and Lister","Lister and Cat","Kryten and Lister","Kryten and Cat", "Rimmer and Cat");


        QuestionsModel questionsModel40 = new QuestionsModel("medium40",
                "What episode is this from?\n\n'Kryten personal black box recording. Time: unkown. Location: unknown. Cause of accident: unknown. " +
                        "Should someone find this recording, perhaps it will shed light on what happened here.'",
                "Terrorform","The Inquisitor","Quarintine","Demons and Angels","Back to Reality","Terrorform");

        QuestionsModel questionsModel41 = new QuestionsModel("medium41",
                "What episode is this from?\n\n'So let me get this straight. You want to fly on a magic carpet to see the King of the Potato " +
                        "People and plead with him for your freedom, and you're telling me you're all completely sane?'",
                "Quarantine","Demons and Angels","Terrorform","Meltdown","Confidence and Paranoia","Quarantine");

        QuestionsModel questionsModel42 = new QuestionsModel("medium42",
                "David Ross, the actor who played the first Kryten, lent his voice to which character in series IV?",
                "Talkie Toaster","Coffee machine on G-Deck","The Chocolate Dispenser","Snacky","Dispenser 55","Talkie Toaster");

        QuestionsModel questionsModel43 = new QuestionsModel("medium43",
                "In 'Thanks for the Memory', Lister asks Rimmer for the time. What answer does a very drunk Rimmer give back?",
                "Time for bed","Saturday","Tomorrow","Yesterday","Smeg off","Saturday");

        QuestionsModel questionsModel44 = new QuestionsModel("medium44",
                "In 'Terrorform' Lister finds a leech attached to his neck. Who does the leech resemble?",
                "Rimmer","Rimmer's mother","Rimmer's father","Cat","Fred Holden","Rimmer's mother");

        QuestionsModel questionsModel45 = new QuestionsModel("medium45",
                "In 'Terrorform' one of Rimmer's personality traits has a tiny gravestone. Which one",
                "Charm","Generosity","Honesty","Courage","Loyalty","Charm");

        QuestionsModel questionsModel46 = new QuestionsModel("medium46",
                "In 'Bodyswap' which of the following dishes was NOT served by Kryten to Rimmer (in Lister's body)?",
                "Mashed potato with cream and butter","Four dozen oysters","Ducks feet in abalone sauce","Roast suckling pig stuffed with chestnuts and truffles",
                "Braised leeks with mozzarella and fried egg", "Braised leeks with mozzarella and fried egg");

        QuestionsModel questionsModel47 = new QuestionsModel("medium47",
                "In 'Gunmen of the Apocalypse' what type of character was Brett Riverboat?",
                "Ace gun slinger","Bare fist fighter","Knife man","River angler","Risk universe champion","Knife man");

        QuestionsModel questionsModel48 = new QuestionsModel("medium48",
                "Upon hearing Lister attempt to play his guitar after exiting a lengthy spell in stasis, who does Kryten tell him he'll play like when his memory returns?",
                "The ghost of Hendrix","The ghost of May","The ghost of Clapton","The ghost of Page","The ghost of Van Halen","The ghost of Hendrix");

        QuestionsModel questionsModel49 = new QuestionsModel("medium49",
                "After the Polymorph sucks his anger out, what is written on the t-shirt Rimmer is seen wearing?",
                "Give quiche a chance","Can't we all just get along","Beards are for warriors","Only smegheads don't wear sandals","Save the space weevils",
                "Give quiche a chance");

        QuestionsModel questionsModel50 = new QuestionsModel("medium50",
                "What was in the cocktail Holly once made for Kryten?",
                "Vimto and liquid nitrogen","Vimto and antifreeze","Vimto and motor oil","Vimto and rocket fuel","Vimto and rum","Vimto and liquid nitrogen");

        QuestionsModel questionsModel51 = new QuestionsModel("medium51",
                "In 'Parallel Universe' how do the crew travel to the other dimension?",
                "Through a white hole.","Through a white hole","So, what is it? I'm guessing it's a white hole.","The Holly Hop Drive","Kidnapped by aliens","The Holly Hop Drive");

        QuestionsModel questionsModel52 = new QuestionsModel("medium52",
                "In 'Timeslides' who guest stars as the celebrity reporter?",
                "Ruby Wax","Pamela Anderson","Samantha Fox","Pete Tranter's sister","Chloe Annett","Ruby Wax");

        QuestionsModel questionsModel53 = new QuestionsModel("medium53",
                "Complete the tattoo on Lister's thigh:\n\n'I Love _____'",
                "Vindaloo","Kochanski","Rimmer","Petersen","My Dad","Petersen");

        QuestionsModel questionsModel54 = new QuestionsModel("medium54",
                "In 'Polymorph' the Polymorph took the form of Rimmer's mother and bedded Lister, when Rimmer found out he was furious, but the mention of what" +
                        " food used as a sexual aid pushed him over the edge?",
                "Alphabetti Spaghetti","Cream Cheese","Vindaloo","Mashed Potato","Custard","Alphabetti Spaghetti");

        QuestionsModel questionsModel55 = new QuestionsModel("medium55",
                "On Justice World the Justice Zone enforces law and order, in 'Samsara' this technology was adapted to enforce morality, what was the newer machine called?",
        "Karma Drive","Justice Drive","You'll Get Yours Drive","Retribution Drive","Peace Drive","Karma Drive");

        QuestionsModel questionsModel56 = new QuestionsModel("medium56",
                "Which was Rimmer's \"all time favourite fascist dictator\"?",
                "Napoleon","Hitler","Mussolini","Stalin","Franco","Napoleon");

        QuestionsModel questionsModel57 = new QuestionsModel("medium57",
                "In which episode did Robert Llewellyn first appear as Kryten?",
                "Kryten","Backwards","Dimension Jump","Parallel Universe","The End","Backwards");

        QuestionsModel questionsModel58 = new QuestionsModel("medium58",
                "In 'Demons and Angels' the Low Red Dwarf crew forced Lister to eat what?",
                "A tarantula","A scorpion","A rancid sock","A Pot Noodle","Maggots","A tarantula");

        QuestionsModel questionsModel59 = new QuestionsModel("medium59",
                "In 'Meltdown' who did Lister witness being executed?",
                "Winnie the Pooh","Mickey Mouse","Jim Bexley Speed","Popeye","Noddy","Winnie the Pooh");

        QuestionsModel questionsModel60 = new QuestionsModel("medium60",
                "In 'Stasis Leak' what was Captain Hollister wearing that made Rimmer think he was hallucinating again?",
                "A chicken costume","A dinasour costume","A bear costume","A mechanoid costume","A priest costume","A chicken costume");

        QuestionsModel questionsModel61 = new QuestionsModel("medium61",
                "In 'Marooned' what type of planet were Lister and Rimmer stranded on?",
                "Ice planet","Desert planet","Gas planet","Water planet","Psiren home planet","Ice planet");

        QuestionsModel questionsModel62 = new QuestionsModel("medium62",
                "In 'Back to Earth' the Katerina Bartikovsky hallucination claims to have served as what on Red Dwarf in the past?",
                "Science Officer","First Technician","Flight Coordinator","Chief Engineer","Prison Warden","Science Officer");

        QuestionsModel questionsModel63 = new QuestionsModel("medium63",
                "In 'Back to Earth' what had the president of the Red Dwarf Fan Club legally changed his name to?",
                "Reg Warf", "Dave Lister","Arnold Rimmer","El Presidente","Dan McGrew","Reg Warf");

        QuestionsModel questionsModel64 = new QuestionsModel("medium64",
                "Who was Rimmer talking about?\n\n\"He doesn't see my good side, my guile, my weasel cunning. When the going " +
                        "gets tough, my ability to find good hiding places.\"",
                "Captain Hollister","Legion","Napolean","Ace","Frank Todhunter","Captain Hollister");

        QuestionsModel questionsModel65 = new QuestionsModel("medium65",
                "Who was Rimmer describing?\n\n" +
                        "\"You've seen what he's like: grizzly bears run screaming from him. Last week he was playing poker, ran out of money - " +
                        "he bet his right nut on a pair of Jacks. A pair of Jacks!!\"",
                "Baxter","Kill Crazy","Big Meat","Petersen","Warden Ackerman","Baxter");

        QuestionsModel questionsModel66 = new QuestionsModel("medium66",
                "Who said it?\n\n\"From now on I'm your jiggly-wiggly, roll-over, sweet-potooey, honey-bun missy! I just wan' make you happy!\"",
                "Baxter","Big Meat","Rimmer","Able","Marilyn Monroe","Big Meat");

        QuestionsModel questionsModel67 = new QuestionsModel("medium67",
                "When Kryten used the Time Wand to punish Baxter for shoving Cats head into a food dispenser, what did he do?",
                "Turned his chicken dinner back to a living chicken","Turned his beef dinner back to a living cow","Turned him back to a toddler",
                "Froze him and stole his ping-pong ball","Froze him and stole his pants","Turned his chicken dinner back to a living chicken");

        QuestionsModel questionsModel68 = new QuestionsModel("medium68",
                "What is added to dinosaur Pete's cow vindaloo to help with getting the Time Wand back?",
                "Bran","Imodium","Lager","Ghost peppers","Garlic","Bran");

        QuestionsModel questionsModel69 = new QuestionsModel("medium69",
                "According to Rimmer, when is the only time Captain Hollister moves quickly?",
                "When passing a salad bar","When Rimmer approaches him","When dinner is being served","When Red Dwarf re-runs are on",
                "When the donuts are done","When passing a salad bar");

        QuestionsModel questionsModel70 = new QuestionsModel("medium70",
                "Who was Rimmer speaking about?\n\n\"Admire him? A man who has his own cinema pick & mix factory in his quarters? A man who " +
                        "has a walk-in fridge? Who lists as his hobbies 'chewing' and 'swallowing'?\"",
                "Captain Hollister","Lister","His father","Petersen","George McIntyre","Captain Hollister");

        QuestionsModel questionsModel71 = new QuestionsModel("medium71",
                "In series VIII who/what was Archie?",
                "Kryten's penis","A food dispenser","A computer Holly is playing chess with","The officer Lister thinks Kochanski is dating",
                "A Canary","Kryten's penis");




        mediumQuestionsArray.add(questionsModel1);
        mediumQuestionsArray.add(questionsModel2);
        mediumQuestionsArray.add(questionsModel3);
        mediumQuestionsArray.add(questionsModel4);
        mediumQuestionsArray.add(questionsModel5);
        mediumQuestionsArray.add(questionsModel6);
        mediumQuestionsArray.add(questionsModel7);
        mediumQuestionsArray.add(questionsModel8);
        mediumQuestionsArray.add(questionsModel9);
        mediumQuestionsArray.add(questionsModel10);
        mediumQuestionsArray.add(questionsModel11);
        mediumQuestionsArray.add(questionsModel12);
        mediumQuestionsArray.add(questionsModel13);
        mediumQuestionsArray.add(questionsModel14);
        mediumQuestionsArray.add(questionsModel15);
        mediumQuestionsArray.add(questionsModel16);
        mediumQuestionsArray.add(questionsModel17);
        mediumQuestionsArray.add(questionsModel18);
        mediumQuestionsArray.add(questionsModel19);
        mediumQuestionsArray.add(questionsModel20);
        mediumQuestionsArray.add(questionsModel21);
        mediumQuestionsArray.add(questionsModel22);
        mediumQuestionsArray.add(questionsModel23);
        mediumQuestionsArray.add(questionsModel24);
        mediumQuestionsArray.add(questionsModel25);
        mediumQuestionsArray.add(questionsModel26);
        mediumQuestionsArray.add(questionsModel27);
        mediumQuestionsArray.add(questionsModel28);
        mediumQuestionsArray.add(questionsModel29);
        mediumQuestionsArray.add(questionsModel30);
        mediumQuestionsArray.add(questionsModel31);
        mediumQuestionsArray.add(questionsModel32);
        mediumQuestionsArray.add(questionsModel33);
        mediumQuestionsArray.add(questionsModel34);
        mediumQuestionsArray.add(questionsModel35);
        mediumQuestionsArray.add(questionsModel36);
        mediumQuestionsArray.add(questionsModel37);
        mediumQuestionsArray.add(questionsModel38);
        mediumQuestionsArray.add(questionsModel39);
        mediumQuestionsArray.add(questionsModel40);
        mediumQuestionsArray.add(questionsModel41);
        mediumQuestionsArray.add(questionsModel42);
        mediumQuestionsArray.add(questionsModel43);
        mediumQuestionsArray.add(questionsModel44);
        mediumQuestionsArray.add(questionsModel45);
        mediumQuestionsArray.add(questionsModel46);
        mediumQuestionsArray.add(questionsModel47);
        mediumQuestionsArray.add(questionsModel48);
        mediumQuestionsArray.add(questionsModel49);
        mediumQuestionsArray.add(questionsModel50);
        mediumQuestionsArray.add(questionsModel51);
        mediumQuestionsArray.add(questionsModel52);
        mediumQuestionsArray.add(questionsModel53);
        mediumQuestionsArray.add(questionsModel54);
        mediumQuestionsArray.add(questionsModel55);
        mediumQuestionsArray.add(questionsModel56);
        mediumQuestionsArray.add(questionsModel57);
        mediumQuestionsArray.add(questionsModel58);
        mediumQuestionsArray.add(questionsModel59);
        mediumQuestionsArray.add(questionsModel60);
        mediumQuestionsArray.add(questionsModel61);
        mediumQuestionsArray.add(questionsModel62);
        mediumQuestionsArray.add(questionsModel63);
        mediumQuestionsArray.add(questionsModel64);
        mediumQuestionsArray.add(questionsModel65);
        mediumQuestionsArray.add(questionsModel66);
        mediumQuestionsArray.add(questionsModel67);
        mediumQuestionsArray.add(questionsModel68);
        mediumQuestionsArray.add(questionsModel69);
        mediumQuestionsArray.add(questionsModel70);
        mediumQuestionsArray.add(questionsModel71);

    }

    public static void populateHardArray(){

        hardQuestionsArray = new ArrayList<>();

        Query getHardQuestions = myDatabase.child("addedQuestions").child("hard");
        getHardQuestions.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot thisQuestion : dataSnapshot.getChildren()){
                        QuestionsModel question = thisQuestion.getValue(QuestionsModel.class);
                        hardQuestionsArray.add(question);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        QuestionsModel questionsModel1 = new QuestionsModel("hard001",
                "Which of the following is NOT a Space Corps Directive?",
                "Terraformers are expressly forbidden from recreating Swindon",
                "A rabbi shall sacrifice one or more chickens in an attempt to solve a crisis situation",
                "No officer above the rank of mess sergeant is permitted to go into combat with pierced nipples",
                "Suntans will be worn during off-duty hours only",
                "Only officers with false teeth should attempt oral sex in zero gravity",
                "Only officers with false teeth should attempt oral sex in zero gravity");

        QuestionsModel questionsModel2 = new QuestionsModel("hard002",
                "What part did Norman Lovett originally audition for?",
                "Lister","Rimmer", "Cat","Talkie Toaster","Kryten","Rimmer");

        QuestionsModel questionsModel3 = new QuestionsModel("hard003",
                "Which part did Chris Barrie originally audition for?",
                "Rimmer", "Lister", "Cat","Holly","Kryten","Lister");

        QuestionsModel questionsModel4 = new QuestionsModel("hard004",
                "What age does Lister tell Rimmer he lost his virginity at?",
                "11","12","13","14","15","12");

        QuestionsModel questionsModel5 = new QuestionsModel("hard005",
                "In which season of Red Dwarf do Normal Lovett and Robert Llewellyn first appear on screen together?",
                "Season 2", "Season 3","Season 5","Season 7", "Season 8", "Season 8");

        QuestionsModel questionsModel6 = new QuestionsModel("hard006",
                "What happened that postponed filming of season one?",
                "Writers strike","Electricians strike", "Red Dwarf model was damaged in a fall",
                "Actors had other commitments","BBC temporarily pulled funding","Electricians strike");

        QuestionsModel questionsModel7 = new QuestionsModel("hard007",
                "Whose voice is the very first we hear in episode one?",
                "Holly","Lister","Rimmer","Captain Hollister","Petersen","Lister");

        QuestionsModel questionsModel8 = new QuestionsModel("hard008",
                "During the titles from series one what letter can Lister be seen painting?",
                "R","E","D","W","F","F");

        QuestionsModel questionsModel9 = new QuestionsModel("hard009",
                "Who said it?\n\n'I'm not sure I can do this. This is the first time I've been seduced by pre-determinism theory.'",
                "Rimmer","Lister","Kochanski","Captain Hollister","Kryten","Kochanski");

        QuestionsModel questionsModel10 = new QuestionsModel("hard010",
                "Which was the last episode to have a contribution from Rob Grant?",
                "Out of Time","Queeg","Polymorph","Nanarchy","Meltdown","Out of Time");

        QuestionsModel questionsModel11 = new QuestionsModel("hard011",
                "Who was Kryten talking about?\n\n'Look at him. The big lug. I'd hate to clean the bath out after him. He'd need a sander to get rid of the" +
                        " tide mark and a leaf vac to hoover the hair.'",
                "Baxter", "Kill Crazy","Lister","A simulant","Warden Ackerman","Baxter");

        QuestionsModel questionsModel12 = new QuestionsModel("hard012",
                "What episode does Baxter say this in?\n\n'Your two mates gulped my hooch. And when they get out of hospital, and there's no guards arounds, " +
                        "this is what's going to happen to them' [squeezes two bread rolls and laughs maniacally]",
                "Only the Good","Krytie TV", "Cassandra","Pete: Part I", "Pete: Part II", "Only the Good");


        QuestionsModel questionsModel13 = new QuestionsModel("hard013",
                "In which episode is this said to Kochanski?\n\n'You're not good enough for him. That's all. OK, he may walk around smelling around smelling like a " +
                        "Balti House laundry basket, but he doesn't need the likes of you swapping dimensions " +
                        "like there's no tomorrow and bewitching him with your...in and out bits. Pointy and unnecessary.'",
                "Ouroboros", "Blue","Duct Soup","Beyond a Joke","Epideme","Ouroboros");

        QuestionsModel questionsModel14 = new QuestionsModel("hard014",
                "Who said this to Kryten?\n\n'Give that guy an eyebrow! Hey, I'm feeling generous! Give him two!'",
                "Confidence","Camille","Cassandra","Ace","Epideme","Epideme");


        QuestionsModel questionsModel15 = new QuestionsModel("hard015",
                "In which episode did Rimmer say this?\n\n'No, the last thing they'll be expecting is for us to turn into ice-skating mongooses " +
                        "and dance the Bolero. And your plan makes as much sense.'",
                "Gunmen of the Apocalypse","Emohawk: Polymorphy II", "The Beginning","Twentica","Trojan","Gunmen of the Apocalypse");

        QuestionsModel questionsModel16 = new QuestionsModel("hard016",
                "In what episode did Cat say this?\n\n'Personally, I thought it started well but fell apart. All the stuff about the ducks getting into " +
                        "trouble - that was great. But then it went black and white and I fell asleep.'",
                "Holoship","Krytie TV","Duct Soup","Ouroboros","Meltdown","Holoship");

        QuestionsModel questionsModel17 = new QuestionsModel("hard017",
                "In the episode 'Quarantine', what does crazy Rimmer play on repeat for the quarantined crew?",
                "Reggie Dixon's Tango Treats", "John Denver's Greatest Hits","Glenn Miller's Greatest Hits",
                "Kevin Keegan's 'Football, It's a Funny Old Game'", "Ronald Johnson's Cabaret Hits", "Reggie Dixon's Tango Treats");

        QuestionsModel questionsModel18 = new QuestionsModel("hard018",
                "In 'Quarantine', which of these items has Rimmer NOT left in the storage cabinet for the quarantined crew?",
                "Chess set, missing 31 pieces","Knitting magazine with a pull-out special on crocheted hats",
                "Puzzle magazine with all the crosswords completed","DIY video on wallpapering, painting and stippling","Two tennis rackets and a squashed table-tennis ball",
                "Two tennis rackets and a squashed table-tennis ball");

        QuestionsModel questionsModel19 = new QuestionsModel("hard019",
                "What episode is this from?\n\n'Rude alert! Rude alert! An electrical fire has knocked out my voice recognition unicycle! Many Wurlitzers are missing from my database. " +
                        "Abandon shop! This is not a daffodil. Repeat, this is not a daffodil.'",
                "Demons and Angels","Blue","Dimension Jump","Terrorform","Lemons","Demons and Angels");


        QuestionsModel questionsModel20 = new QuestionsModel("hard020",
                "Which episode does Chris Barrie say is his favourite?",
                "Dimension Jump","Marooned","Rimmerworld","Quarantine","Me^2","Dimension Jump");

        QuestionsModel questionsModel21 = new QuestionsModel("hard021",
                "Which episode does Craig Charles say is his favourite?",
                "Marooned","Timeslides","Krytie TV","Parallel Universe","Back to Earth","Marooned");

        QuestionsModel questionsModel22 = new QuestionsModel("hard022",
                "Which episode does Robert Llewellyn say is his favourite?",
                "Krytie TV", "DNA","Camille","Emohawk: Ploymorph II","Backwards","Krytie TV");

        QuestionsModel questionsModel23 = new QuestionsModel("hard023",
                "Which episode does Norman Lovett say is his favourite episode?",
                "Queeg","The End","Parallel Universe","Back in the Red, Part III","Future Echoes","Queeg");

        QuestionsModel questionsModel24 = new QuestionsModel("hard024",
                "What film star do the skutters enjoy watching?",
                "John Wayne","Clint Eastwood","Roy Rogers","Gene Autry","Ben Johnson","John Wayne");

        QuestionsModel questionsModel25 = new QuestionsModel("hard025",
                "In 'Gunmen of the Apocalypse' what drink does Cat order in the saloon?",
                "Tequila","Whiskey","Beer","Dry white wine and Perrier","Sex on the beach","Tequila");

        QuestionsModel questionsModel26 = new QuestionsModel("hard026",
                "What was the name of the company that manufactured the Talkie Toaster?",
                "CRAPOLA INC.","SMEGOLA INC.","RIPOFF INC.","MINDGAMES INC.","TALKIE TIME INC.","CRAPOLA INC.");

        QuestionsModel questionsModel27 = new QuestionsModel("hard027",
                "After Holly is powered down to conserve her remaining runtime, also cutting electricity to the ships doors, how long does it take the crew to get to the cargo deck and back?",
                "5 days","3 days","7 days","1 day","10 days","5 days");

        QuestionsModel questionsModel28 = new QuestionsModel("hard028",
                "Before Krytie TV is launched, what actors movies do the prisoners have to look forward to?",
                "George Formby","Noel Coward","Charlie Chaplin","Stanley Holloway","Bernard Cribbins","George Formby");

        QuestionsModel questionsModel29 = new QuestionsModel("hard029",
                "What does Holly say is \"usually followed by a cremation and some sandwiches\"?",
                "A breakup","A London Jets game","A trip to Swindon","Meeting a simulant","Rimmer's Risk nights","A breakup");

        QuestionsModel questionsModel30 = new QuestionsModel("hard030",
                "In 'Queeg' what distorted audio-book read by 'Robert Hardy' is Cat listening to?",
                "Tess of the D'Urbervilles","Far from the Madding Crowd","Jude the Obscure","The Mayor of Casterbridge",
                "Football, It's a Funny Old Game","Tess of the D'Urbervilles");

        QuestionsModel questionsModel31 = new QuestionsModel("hard031",
                "What was Queeg's full title?",
                "Queeg 5","Queeg 50","Queeg 500","Queeg 5000","Queeg 90","Queeg 500");

        QuestionsModel questionsModel32 = new QuestionsModel("hard032",
                "In 'Thanks for the Memory' what does drunk Lister desire more than anything?",
                "A triple fried egg butty with chili sauce and chutney", "A triple fried egg butty with vindaloo sauce",
                "A double fried egg butty with chili sauce and chutney", "A double fried egg butty with vinadloo sauce",
                "A fried egg butty with chili sauce and chutney", "A triple fried egg butty with chili sauce and chutney");

        QuestionsModel questionsModel33 = new QuestionsModel("hard033",
                "Who sings this?\n\n" +
                        "'I'm a little lamb, lost in the wood, maybe I could, really be good, with someone to watch over me'",
                "Rimmer","Lister","Cat","Holly","Kryten","Rimmer");

        QuestionsModel questionsModel34 = new QuestionsModel("hard034",
                "In 'Thanks for the Memory' what was the name of Lister's ex-girlfriend in the memory given to Rimmer?",
                "Lise Yates","Yvonne McGruder","Lisa McGruder","Yvonne Yates","Fiona Barrington","Lise Yates");

        QuestionsModel questionsModel35 = new QuestionsModel("hard035",
                "What comes next?\n\n" +
                        "Kryten: 'Non-space sir. An abyss of infinite nothingness, where time doesn't seem to exist.'" +
                        "\nLister: 'Sounds like Rimmer's __________'",
                "Organ recital night","Risk game night","Holiday photos presentation","Company","Stories","Organ recital night");

        QuestionsModel questionsModel36 = new QuestionsModel("hard036",
                "In 'Ouroboros' what order does Kochanski give to Rimmer?",
                "\"Have sex with someone\"","\"Smeg off\"","\"Stop talking to me\"","\"Grow a spine\"","\"Stop being a gimp\"",
                "\"Have sex with someone\"");


        QuestionsModel questionsModel37 = new QuestionsModel("hard037",
                "In 'Duct Soup', what does Lister do after he states that he's \"too mature\" to watch Kochanski's underwear spin drying?",
                "Reads a comic","Bites his toenails","Watches the underwear spin drying again","Belches loudly","Picks his nose","Reads a comic");

        QuestionsModel questionsModel38 = new QuestionsModel("hard038",
                "Who said it?\n\n" +
                        "'It's so damn hot I can hardly breathe! It's like being stuck in a sauna with a fat man on your face!'",
                "Cat","Rimmer","Lister","Kochanski","Captain Hollister","Cat");

        QuestionsModel questionsModel39 = new QuestionsModel("hard039",
                "Who said it?\n\n" +
                        "'That's what you used to call him, is it? \"Hey Bent Bob! How's it going, mate?\"'",
                "Kochanski","Cat","Lister","Rimmer","Kryten","Kochanski");

        QuestionsModel questionsModel40 = new QuestionsModel("hard040",
                "Who was it said to?\n\n" +
                        "'Just cross-filing that story under 'B' for blackmail, and 'A' for anecdote; subcategory 'S' for 'so funny you'll laugh til you're sick'!'",
                "Rimmer","Lister","Holly","Kochanski","Cat","Kochanski");

        QuestionsModel questionsModel41 = new QuestionsModel("hard041",
                "When Holly asks Lister to delete novels from his memory so he can read them again, what author does he mention?",
                "Agatha Christie","Ruth Rendell","Ellis Peters","Catherine Aird","Margery Allingham","Agatha Christie");

        QuestionsModel questionsModel42 = new QuestionsModel("hard042",
                "What name did Yvonne MacGruder keep calling Rimmer when she was concussed?",
                "Norman","Nigel","Neil","Niles","Norris","Norman");

        QuestionsModel questionsModel43 = new QuestionsModel("hard043",
                "What comes next?\n\n'Hey, this has been a good day. I've eaten 5 times, I've slept 6 times, and I've made a lot of things mine. Tomorrow, I'm gonna see if I can't _______'",
                "Have sex with something","Make more stuff mine","Catch a fish","Do it all again","Look even more gorgeous","Have sex with something");

        QuestionsModel questionsModel44 = new QuestionsModel("hard044",
                "What did Cat find?\n\n'S-E-X, you know I want it! S-E-X, I'm gonna get it! S-E-X, I think I found it!'",
                "Lister unconscious in the corridor","Polymorph who had taken the form of a female Felis Sapiens","Kryten's groinal attachment",
                "Rimmer with breasts","A mirror","Lister unconscious in the corridor");

        QuestionsModel questionsModel45 = new QuestionsModel("hard045",
                "Which episode is this from?" +
                        "\n\n'I've seen mirrors. I have eyes. Face it, buddy - I have a body that makes men sweat.'",
                "Justice","White Hole","Kryten","Better Than Life","Stoke me a Clipper","Justice");

        QuestionsModel questionsModel46 = new QuestionsModel("hard046",
                "Who said it?\n\n" +
                        "'He partied less than Rudolf Hess. He was totally dedicated to his career. He was in charge of Z shift, and it " +
                        "occupied his every waking moment.'",
                "Lister","Kryten","Holly","Kochanski","Captain Hollister","Lister");

        QuestionsModel questionsModel47 = new QuestionsModel("hard047",
                "Who said it?\n\n" +
                        "'Under the influence of this psychadelic breakfast he went on to attack two senior officers, believing " +
                        "them to be giraffes who were armed and dangerous.'",
                "Lister","Rimmer","Kryten","Captain Hollister","Warden Ackerman","Rimmer");

        QuestionsModel questionsModel48 = new QuestionsModel("hard048",
                "What episode is this from?\n\n" +
                        "'Petersen, how are you mate? I don't believe it, it's you! I've missed you, you know. " +
                        "Give us a kiss, you smelly-arsed smegger!'",
                "Stasis Leak","Timeslides","Dimension Jump","Back in the Red","Better Than Life","Stasis Leak");

        QuestionsModel questionsModel49 = new QuestionsModel("hard049",
                "When Rimmer and Lister were itemising supplies how many haggis were there?",
                "4691","4671","4695","4681","4699","4691");

        QuestionsModel questionsModel50 = new QuestionsModel("hard050",
                "In the TV show Cat's ancestors wage holy wars for 1000's of years over the colour of hats. In the books what reason was given?",
                "The name of their God - Clister or Cloister","What outfits are acceptable to accessorise with peach trim and gold spangles","The name of their promised land - Bearth or Fuchal",
                "Over a debate about whether or not two legged species should be using a litter-box","Over them all claiming everything to be their own property",
                "The name of their God - Clister or Cloister");

        QuestionsModel questionsModel51 = new QuestionsModel("hard051",
                "In 'Lemons' we find out that Rimmer's mother believed Judas took Jesus's place on the cross, which allowed Jesus to move to the south of France and invent what?",
                "Wheelbarrow","Guillotine","Football","Deodorant","Digital Watch","Wheelbarrow");

        QuestionsModel questionsModel52 = new QuestionsModel("hard052",
                "In which episode do we first meet Duane Dibbley?",
                "Back to Reality","Dimension Jump","Parallel Universe","Better Than Life","Emohawk","Back to Reality");

        QuestionsModel questionsModel53 = new QuestionsModel("hard053",
                "What is the name of the suite on Red Dwarf that has the central controls for the hologram technology?",
                "Hologram Simulation Suite","Hologram Projection Suite","Hologram Creation Suite","Hologram Control Suite",
                "Hologram Generation Suite","Hologram Simulation Suite");

        QuestionsModel questionsModel54 = new QuestionsModel("hard054",
                "Which ship contained the bio-printer, used by Rimmer to create multiple clones of himself?",
                "Nova 5","SS Nautilus","Leviathan","Wildfire","Enlightenment","SS Nautilus");

        QuestionsModel questionsModel55 = new QuestionsModel("hard055",
                "In 'Back to Earth' where does Kryten take off to on a weeks holiday?",
                "Broom cupboard on V deck","Ironing closet on V deck","Maintenance closet on V deck","Broom cupboard on B deck","Ironing closet on B deck",
                "Broom cupboard on V deck");

        QuestionsModel questionsModel56 = new QuestionsModel("hard056",
                "In 'Can of Worms' what are the Nahki Ninkas GELF tribe more commonly known as?",
                "Vampire GELFs","Demon GELFs","Warlock GELFs","Cannibal GELFs", "Canine GELFs","Vampire GELFs");

        QuestionsModel questionsModel57 = new QuestionsModel("hard057",
                "In 'Can of Worms' what is the name of the female felis sapiens prisoner found aboard the transport ship?",
                "Ankita", "Caterina","Ankitty","Catalina","Veronicat","Ankita");

        QuestionsModel questionsModel58 = new QuestionsModel("hard058",
                "Which episode is this from?\n\n" +
                        "\"According to the psi-scan, the membrane between two realities has temporary collapsed. This is some " +
                        "kind of 'hyperway', through non-space to a parallel dimension.\"",
                "OUROBOROS","Dimension Jump","Stasis Leak","Parallel Universe","Timeslides","OUROBOROS");

        QuestionsModel questionsModel59 = new QuestionsModel("hard059",
                "Which episode is this from?\n\n"+
                "\"The Canaries....you know what they say it's supposed to stand for? 'Convicts Army Nearly All Retarded In-bred Evil Sheep-shaggers'...\"",
                "Cassandra", "Back in the Red","Krytie TV","Pete","Only the Good","Cassandra");

        QuestionsModel questionsModel60 = new QuestionsModel("hard060",
                "Which episode is this from?\n\n" +
                        "\"See? I even have to learn new terminology, special female terminology: 'cups', 'pot pourri', 'depilatory cream' - oh! It's never-ending.\"",
                "Blue","Duct Soup","Beyond a Joke","Tikka to Ride","Stoke me a Clipper","Blue");

        hardQuestionsArray.add(questionsModel1);
        hardQuestionsArray.add(questionsModel2);
        hardQuestionsArray.add(questionsModel3);
        hardQuestionsArray.add(questionsModel4);
        hardQuestionsArray.add(questionsModel5);
        hardQuestionsArray.add(questionsModel6);
        hardQuestionsArray.add(questionsModel7);
        hardQuestionsArray.add(questionsModel8);
        hardQuestionsArray.add(questionsModel9);
        hardQuestionsArray.add(questionsModel10);
        hardQuestionsArray.add(questionsModel11);
        hardQuestionsArray.add(questionsModel12);
        hardQuestionsArray.add(questionsModel13);
        hardQuestionsArray.add(questionsModel14);
        hardQuestionsArray.add(questionsModel15);
        hardQuestionsArray.add(questionsModel16);
        hardQuestionsArray.add(questionsModel17);
        hardQuestionsArray.add(questionsModel18);
        hardQuestionsArray.add(questionsModel19);
        hardQuestionsArray.add(questionsModel20);
        hardQuestionsArray.add(questionsModel21);
        hardQuestionsArray.add(questionsModel22);
        hardQuestionsArray.add(questionsModel23);
        hardQuestionsArray.add(questionsModel24);
        hardQuestionsArray.add(questionsModel25);
        hardQuestionsArray.add(questionsModel26);
        hardQuestionsArray.add(questionsModel27);
        hardQuestionsArray.add(questionsModel28);
        hardQuestionsArray.add(questionsModel29);
        hardQuestionsArray.add(questionsModel30);
        hardQuestionsArray.add(questionsModel31);
        hardQuestionsArray.add(questionsModel32);
        hardQuestionsArray.add(questionsModel33);
        hardQuestionsArray.add(questionsModel34);
        hardQuestionsArray.add(questionsModel35);
        hardQuestionsArray.add(questionsModel36);
        hardQuestionsArray.add(questionsModel37);
        hardQuestionsArray.add(questionsModel38);
        hardQuestionsArray.add(questionsModel39);
        hardQuestionsArray.add(questionsModel40);
        hardQuestionsArray.add(questionsModel41);
        hardQuestionsArray.add(questionsModel42);
        hardQuestionsArray.add(questionsModel43);
        hardQuestionsArray.add(questionsModel44);
        hardQuestionsArray.add(questionsModel45);
        hardQuestionsArray.add(questionsModel46);
        hardQuestionsArray.add(questionsModel47);
        hardQuestionsArray.add(questionsModel48);
        hardQuestionsArray.add(questionsModel49);
        hardQuestionsArray.add(questionsModel50);
        hardQuestionsArray.add(questionsModel51);
        hardQuestionsArray.add(questionsModel52);
        hardQuestionsArray.add(questionsModel53);
        hardQuestionsArray.add(questionsModel54);
        hardQuestionsArray.add(questionsModel55);
        hardQuestionsArray.add(questionsModel56);
        hardQuestionsArray.add(questionsModel57);
        hardQuestionsArray.add(questionsModel58);
        hardQuestionsArray.add(questionsModel59);
        hardQuestionsArray.add(questionsModel60);
    }


    public static void populateQuotesArray(){
        quotesArray = new ArrayList<>();

        String quote1 = "\"Now kindly cluck off, before I extract your giblets, and shove a large seasoned onion between the lips you never kiss with.\"\n" +
                "\n" +
                "-\tRimmer, Stasis Leak";

        String quote2 = "\"Call it extreme if you like, but I propose we hit it hard and hit it fast with a major - and I mean major - leaflet campaign.\"\n" +
                "\n" +
                "-\tRimmer, Polymorph";

        String quote3 = "\"I've been so worried I haven't buffed my shoes in my two days.\"\n" +
                "\n" +
                "- Cat, Marooned";

        String quote4 = "Rimmer: \"I've seen Westerns, I know how to speak cowboy.\"\n" +
                "\n" +
                "[Steps up to the bar]\n" +
                "\n" +
                "Rimmer: \"Dry white wine and Perrier, please.\"\n" +
                "\n" +
                "- Rimmer, Gunmen of the Apocalypse";

        String quote5 = "“I tell you one thing. I've been to a parallel universe, I've seen time running backwards, I've played pool with planets, and I've given birth to twins, but I never thought in my entire life I'd taste an edible Pot Noodle.\"\n" +
                "\n" +
                "-\tLister, Demons and Angels";

        String quote6 = "It's better to have loved and lost than to listen to an album by Olivia Newton-John.\n" +
                "\n" +
                "-\tHolly, Stasis Leak";

        String quote7 = "\"Has anyone ever told you that the configuration and juxtaposition of your features is extraordinarily apposite?\"\n" +
                "\n" +
                "-\tKryten, Camille";

        String quote8 = "Of course, lager! The only thing that can kill a vindaloo!\n" +
                "\n" +
                "-\tLister, D.N.A.";

        String quote9 = "How come you need more memory? Over the years you've had more RAM than a field of sheep!\n" +
                "\n" +
                "-\tCat, Tikka to Ride";

        String quote10 = "\"I'm so gorgeous, there's a six month waiting list for birds to suddenly appear, every time I am near!\"\n" +
                "\n" +
                "-\tCat, Back in the Red";

        String quote11 = "\"Rude alert! Rude alert! An electrical fire has knocked out my voice recognition unicycle! Many Wurlitzers are missing from my database. Abandon shop! This is not a daffodil. Repeat, this is not a daffodil.\"\n" +
                "\n" +
                "-\tHolly, Demons and Angels";

        String quote12 = "\"So this is really me? A no-style gimbo with teeth the Druids could use as a place of worship?\"\n" +
                "\n" +
                "-\tDuane Dibbley, Back to Reality";



        quotesArray.add(quote1);
        quotesArray.add(quote2);
        quotesArray.add(quote3);
        quotesArray.add(quote4);
        quotesArray.add(quote5);
        quotesArray.add(quote6);
        quotesArray.add(quote7);
        quotesArray.add(quote8);
        quotesArray.add(quote9);
        quotesArray.add(quote10);
        quotesArray.add(quote11);
        quotesArray.add(quote12);
    }

}
