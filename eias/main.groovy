import javafx.geometry.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Platform;

import org.excelsi.nausicaa.ca.*;

//final WebView browser = new WebView();
//final WebEngine webEngine = browser.getEngine();

incant = { d, s, c, i ->
    new ComputedRuleset(new Archetype(d,s,c)).create(i)
}

unprn = { $c.unprint() }
br = { pause(); clear(); }
anim = { fs ->
    fs.each { prn(it); pause(300); unprn() }
    prn(fs.last())
}
clear();
/*x -> prn(x.ctr()) = { x -> x.style('h1') }*/
pc = { x -> prn(x.ctr()) }
pb = { x -> prn(h2(x)) }
pt = { x -> prn(x.style('text-serif-big')) }
pbs = { x -> prn(h2(x.stripMargin())) }
pbt = { x -> prn(x.stripMargin().style('text-serif-big')) }
txts = { t -> txt(t.stripMargin()) }
pt = { t -> prn(txts(t)) }
title = { x -> x.style('text-serif-bigger') }
h1 = { x -> x.style('h1') }
h2 = { x -> x.style('h2') }
h3 = { x -> x.style('h3') }

class Browser extends Region {

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    public Browser(String url) {
        //apply the styles
        getStyleClass().add("browser");
        // load the web page
        //webEngine.load("http://www.oracle.com/products/index.html");
        //add the web view to the scene
        getChildren().add(browser);

        webEngine.load(url)
    }

    private Node createSpacer() {
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    @Override protected void layoutChildren() {
        double w = getWidth();
        double h = getHeight();
        layoutInArea(browser,0,0,w,h,0, HPos.CENTER, VPos.CENTER);
    }

    @Override protected double computePrefWidth(double height) {
        return 900;
    }

    @Override protected double computePrefHeight(double width) {
        return 600;
    }
}

web = { url ->
    if(Platform.isFxApplicationThread()) {
        def b = new Browser(url)
        return b
    }
    else {
        Platform.runLater( {
            def b = new Browser(url)
            //b.webEngine.load(url)
        })
    }
}

s2 = [
    {
        web("https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/add-browser.htm")
    }
]


// general structure
// non math
// some math
// pre-conway history
// Conway
// conway - wolfram
// wolfram - present day
// extrapolations
// interesting



//100:ki mi a2 a3 u ki mi8 a3 ma ya ra





slides = [
    {
        prn('')
        prn('')
        prn('')
        prn((0..255).collect {
            rules(1,1,2).rule(it).ca([26,26], ['0x1122aa','cyan']) }
            .table(16, ['margin':0])
            .label('EXPLORATIONS IN AUTOMATIC SPACES\njkwhite@', ['pos':'top']).style('sans-cond-bold-large').ctr())
    },
    [
        {
            prn ''
            prn(title('Structure').ctr())
        },
        {
            pbt '''
            |\t1. What are cellular automata?
            
            |\t2. Visual representations of state space
            
            |\t3. One-dimensional automata
            
            |\t4. Two-dimensional automata
            
            |\t5. Practical applications
            
            |\t6. Impractical applications
            
            |\t7. Sources & further reading
            '''
        }
    ],
    [
        {
            prn ''
            prn title('What is a cellular automaton?'.ctr())
        },
        {
            pt '\n\t• A universe of possible states, ...'
        },
        {
            pt '\t• A state of the universe, and...'
        },
        {
            pt '\t• An update rule for deriving new states'
        },
        {
            pt '''
            |\tGiven a state `/S`` and an update rule `/R``, then:
            
            |\t\t• R(S) → S'
            |\t\t• R(S') → S\'\'
            |\t\t• R(R(S)) → S\'\''''
        },
        {
            prn(txt('''
            |\tWhat then distinguishes a cellular automaton from a general function `/f(x)``?
            
            |\t\t• State is discrete; often represented as some kind of spatial lattice
            |\t\t• Time is discrete; entire state is updated at once*
            '''.stripMargin()))
        }
    ],
    [
        {
            prn ''
            prn title('A simple example'.ctr())
        },
        {
            pt('''
            |\t• We will represent the automata state by a 3-element array `/S``
            |\t• We define the initial state of `/S`` as:
            
            |\t\t`cS = [0 1 0] ''')
        },
        {
            pt '''
            |\t• Each element of the array can be addressed by subscript:
            `c
            |\t\tS[0] → 0
            |\t\tS[1] → 1
            |\t\tS[2] → 0 '''
        },
        {
            pt '''
            |\t• Our update rule 'R' will operate on each element of the state array:
            `c
            |\t\tfor i in 0..2:
            |\t\t\tS'[i] = S[i-1] xor S[i+1] '''
        },
        {
            pt '''
            |\t• What about when i is 0 or 2? Our state array is "toroidal" - it wraps around
            `c
            |\t\tS[-1] == S[2]
            |\t\tS[3]  == S[0] '''
        }
    ],
    [
        {
            prn ''
            prn title('A simple example, continued'.ctr())
        },
        {
            pt '''
            |\tRecall the update rule:
            `c
            |\t\tS'[i] = S[i-1] xor S[i+1]
            ``
            |\tHow does our state evolve when the update rule is applied? '''
        },
        {
            pbs '''
            |\t\tS    = [0 1 0]
            |\t\tS\'   = [1 0 1]
            |\t\tS\'\'  = [1 0 1]
            |\t\tS\'\'\' = [1 0 1] '''
        },
        {
            pt '\n\t\t→ The universe has reached a rather dull terminal state'
        },
        {
            pt '\n\tWhat happens if we instead use a 5-element state array with the same rule?'
        },
        {
            pbs '''
            |\t\tS      = [0 0 1 0 0]
            |\t\tS\'     = [0 1 0 1 0]
            |\t\tS\'\'    = [1 0 0 0 1]
            |\t\tS\'\'\'   = [1 1 0 1 1]
            |\t\tS\'\'\'\'  = [0 1 0 1 0] (repeat of S\')
            |\t\tS\'\'\'\'\' = [1 0 0 0 1] (repeat of S\'\') '''
        },
        {
            pt '\n\t\t→ This is somewhat more interesting '
        }
    ],
    [
        {
            prn ''
            prn title('The part where it gets somewhat more interesting'.ctr())
        },
        {
            pt '''
            |\t• The 3-element array evolved to a 'homogeneous state': repeats at each step
            |\t• The 5-element array evolved to a 'periodic state': repeats every 3 steps '''
        },
        {
            pt '\t• Let\'s make state space bigger! But first, we will make two important changes...'
        },
        {
            pt '''
            |\t\t1. Represent evolving state as a two-dimensional matrix
            `c
            |\t\t          0 1 0                     0 0 1 0 0
            |\t\t          1 0 1                     0 1 0 1 0
            |\t\t   S{3} = 1 0 1              S{5} = 1 0 0 0 1
            |\t\t          1 0 1                     1 1 0 1 1
            |\t\t          1 0 1                     0 1 0 1 0
            ``
            |\t\t   → Time is represented as rows; state space as columns'''
        },
        {
            pt '''
            |\t\t2. Represent values in state matrixes... as colors`c
            |\t\t\t • 0 → black
            |\t\t\t • 1 → white
            '''
        },
        {
            //rule = rules(1,1,2).rule(129)
            //test_incant(1,1,2)
            rule = incant(1,1,2,"128:a2 o2 o0 tsu mo")
            bnw = ['black','white']
            prn([
                '', '',
                rule.ca([3, 5], bnw, init_single()).scaled(33).label('S{3}', ['pos':'top']),
                rule.ca([5, 5], bnw, init_single()).scaled(20).label('S{5}', ['pos':'top'])
            ].table(4,['margin':200,'padding':200]).ctr())
        }
    ],
    [
        {
            prn ''
            prn title('Neighborhood #1'.ctr())
        },
        {
            pt '\n\tBut before we go on! Let\'s talk about "neighborhoods": a way of describing locality'
        },
        {
            pt '''
            |\tRecall our example update rule:
            `c
            |\t\tS'[i] = S[i-1] xor S[i+1]
            ``
            |\tIn a "neighborhood of size 1", like our rule, each cell update can only depend on...
            
            |\t\t• Itself (home)
            |\t\t• Immediately-adjacent cells (next-door neighbors)'''
        },
        {
            pt '''
            |\tMore generally,
            `c
            |\t\tS'[i] = R(S[i-1,i,i+1])
            ``
            |\tOr, in a neighborhood of size 2,
            `c
            |\t\tS'[i] = R(S[i-2..i+2])'''
        }
    ],
    [
        {
            prn ''
            prn title('The Reveal'.ctr())
        },
        {
            pt '''
            |\tLet's go back to our simple example and zoom out in state space...
            '''
        },
        {
            rule = incant(1,1,2,"128:a2 o2 o0 tsu mo")
            bnw = ['black','white']
            x=600; y=300
            anim(
                (100..1).step(4).collect { rule.ca([(int) (x/it), (int) (y/it)], bnw, init_single()).scaled(it) }
            )
        },
        {
            pt '''
            |\tIt is a Sierpinski triangle!

            |\tOr rather, it is a discrete approximation of a Sierpinksi triangle.
            '''
        },
        {
            pt '\tThis is an example of what are called "one-dimensional automata".'
        }
    ],
    [
        {
            //77:ki su nu o4 ya ha mi ka ka
            prn ''
            prn(rules(1,1,2).rule(110)
                .ca([200,200], ['black','white'], init_single(1,199,0,0)).scaled(2))
            prn ''
            prn title('Linear Arrays & Fancy Triangles'.ctr())
            prn ''
            pbt('One-dimensional automata'.ctr())
        }
    ],
    [
        {
            prn ''
            prn title('Elementary Automata'.ctr())
        },
        {
            pt '''
            |\tIn the 1980s, Stephen Wolfram described "elementary automata" as having...
            
            |\t\t• One-dimensional
            |\t\t• Size 1 neighborhoods
            |\t\t• Two possible cell state values'''
        },
        {
            pt '''
            |\tHow many elementary automata are there?

            |\t• 3 input states
            |\t• 2 possible values for each input state
            |\t• 1 output state also with 2 possible values

            |\t→ [possible output values] `^[possible input values]`^[input states]
            '''
        },
        {
            pt '\t→ 2`^2`^3`` → 2`^8`` → 256 elementary automata'
        },
        {
            pt '''
            |\tEach rule can be referred to in a kind of Gray code, from 0 to 255.

            |\tBut what do they look like?'''
        }
    ],
    {
        prn(title('Elementary Automata, part 1').ctr())
        prn ''
        bnw = ['black','white']
        prn((0..63).collect { rules(1,1,2).rule(it).ca([50,50], bnw, init_single()).scaled(2).label("Rule ${it}", ['pos':'top']) }.table(13, ['margin':8,'padding':8]).ctr())
    },
    {
        prn(title('Elementary Automata, part 2').ctr())
        prn ''
        bnw = ['black','white']
        prn((64..127).collect { rules(1,1,2).rule(it).ca([50,50], bnw, init_single()).scaled(2).label("Rule ${it}", ['pos':'top']) }.table(13, ['margin':8,'padding':8]).ctr())
    },
    {
        prn(title('Elementary Automata, part 3').ctr())
        prn ''
        bnw = ['black','white']
        prn((128..191).collect { rules(1,1,2).rule(it).ca([50,50], bnw, init_single()).scaled(2).label("Rule ${it}", ['pos':'top']) }.table(13, ['margin':8,'padding':8]).ctr())
    },
    {
        prn(title('Elementary Automata, part 4').ctr())
        prn ''
        bnw = ['black','white']
        prn((192..255).collect { rules(1,1,2).rule(it).ca([50,50], bnw, init_single()).scaled(2).label("Rule ${it}", ['pos':'top']) }.table(13, ['margin':8,'padding':8]).ctr())
    },
    [
        {
            prn ''
            prn title('The importance of initial state'.ctr())
        },
        {
            pt '''
            |\tDoes the same automata evolve differently under different initial conditions?

            |\tLet's test with a single point vs random initial state!
            '''
        },
        {
            prn(
                [110, 137, 146, 184, 54, 90, 106, 26].collect {
                    [
                        rules(1,1,2).rule(it).ca([150,150], ['black','white'], init_single()),
                        rules(1,1,2).rule(it).ca([150,150], ['black','white'], init_random())
                    ].table(2).label("Rule ${it}", ['pos':'bottom'])
                }.table(4, ['margin':30])
            )
        },
        {
            pt '\n\t\t→ Initial conditions influence automata behavior.'
        }
    ],
    [
        {
            prn ''
            prn title('Classifying automata'.ctr())
        },
        {
            pt '''
            |\tAutomata can be classified into four broad behavioral categories.
            '''
        },
        {
            prn(
                [['Class 1: Uniform',0,168,248,255], ['Class 2: Cyclic',91,37,62,73], ['Class 3: Chaotic',22,30,126,182], ['Class 4: Complex',110,137,54,147]].collect {
                    (it.tail().collect { c ->
                        rules(1,1,2).rule(c).ca([150,250], ['black','white'], init_random()).label("Rule ${c}", ['pos':'bottom'])
                    }.table(4)).label(it[0],['pos':'bottom'])
                }.table(2, ['margin':30])
            )
        }
    ],
    [
        {
            prn ''
            prn title('Rule 110: A universal computer'.ctr())
        },
        {
            prn([
                rules(1,1,2).rule(110).ca([500,650], ['black','white'], init_random()).label("Rule 110", ['pos':'bottom']),
                txts('''
                |\t• Class 4 (complex) automata

                |\t• Proof of Turing-completeness by Matthew Cook

                |\t• Builds on universality proof for Life

                |\t• Emulates a cyclic tag system

                |\t• Embedded state in background
                ''')
            ].table(2))
        }
    ],
    [
        {
            prn ''
            prn title('Beyond elementary automata'.ctr())
        },
        {
            pt '''
            |\tThere are many more one-dimensional automata than just the elementary.

            |\tWe can increase the neighborhood size and number of 'colors' to find more!'''
        },
        {
            pt '''
            |\tLet us consider size-2 neighborhoods with 4 colors.

            |\tHow many such automata are there? Well...'''
        },
        {
            pt '''
            |\t• 5 input states (nearest and next-nearest)
            |\t• 4 possible values for each input state
            |\t• 1 output state also with 4 possible values

            |\t→ [possible output values] `^[possible input values]`^[input states]
            '''
        },
        {
            pt '''\t→ 4`^4`^5`` → 4`^1024`` → 10`^616`` automata

            |\t\t→ `/That's quite a lot. Let's look at a few...'''
        }
    ],
    [
        {
            prn ''
            prn(txt('\t\t     10`^616`` bottles of beer on the wall...','text-big').ctr())
        },
        {
            prn(
                ["97:ne go go ka ge ni ro7 ma ta1 ma ta1 wo",
                 "97:ne go go go be yu ma ta3 o2 no bo be ta ki go be yu ma ta3 o2 no bo be ta ki do u tsu me e",
                 "97:hi ne go go be yu go mi chi pa ma ke o3 shi bo be ta hu ki o0 ma ta7 bo mi8 ji ta ki do do u me tsu ki be tsu he",
                 "97:hi ne go go be yu ne go mi chi1 pa chi ma ke o4 shi bo be ta4 hu ki ma o0 bo ku no no ji ta ki do ku do u ya ki ki bu tsu",
                 "97:hi ne go bu go hi ne mi chi1 pa ya ke shi o3 bo bu ta4 ki ma o0 bo ku sa ji no go ki do ku ho u ya ya ki wo be ki tsu ba mo hi ka",
                 "97:ki ta tsu hi ji ma ya do chi1 pa no ya o0 sa sa u ku mo mi shi ya ki ko ne go ne bo bu bo ki ta o3 be go go wo o5 be go go wo ba ho ki ji hi ke go e ji na sa",
                 "97:ta10 hi tsu ki ya ma do chi pa no o0 hu sa ya ga mo mi mi shi ki ko no ne bo go ra bo ki ta6 o2 o1 bu ba go go wo o5 gu a24 o5 be ge ya wo go go wo o2 a19.904884338378906 po gu o5 be ge go o4 ba ki shi ya ra ne go ta1 bo bu ne yu ki o0 bu go a5 wo go o4 o5 go gu o5 no ge ya wo go go wo o3 a19.904884338378906 gu bu ge go o3 ba ki ya shi ra ne ni go ta2 bo be ne yu ki o0 go bu go wo o4 ki o5 bu go go ba ko ho nu ri ki ki ki ji ke hi go e mi4 mi6 mi6 ho na ji a23 ke hi go ho e na nu ya ki ha ri bo ge pe a17.709860801696777 ha mo ge zu gu go ha",
                 "97:no ki ge bo bo be chi zu6 tsu go hu nu wo no wo nu a19.904884338378906 pe do wo no o5 ri go na ki po bu shi o0 o4 mi6 ki ne be ne bo ji ne go e ri ji go o4 pe yu bo a14 wo ke o5 mi ho ne gu ge ki o5 a22.324371337890625 no no sa o2 gu o0 ki po gu ki ya ra ne bu go jo go pa ha be ge ri ta1 ge ko o0 jo o0 hi ya ke o5 mi4 o5 bo o4 ya mo wo o0 gu ko ko go ko ge ya a23.83755087852478 ka ge ge ho na ki ya gu gu hi o2 yu no ge ba ra go na e ja ge mi ge ha go ja go ki go gu pa ba be go mi7 ho ra mo na e ja ge mi ge go go ki go gu pa ba be go mi4 ra mo a18.548187732696533 o0 a6.59570574760437 mu u go ta10 o4 mi tsu ma hi ni shi o5 ba o5 o5 gu ki a21.1857248544693 go ki ya mi5 e ba go po ha ri shi bo ta2 shi ro3 ta6 ra be po wa ha ne pe ja jo hi",
                 "97:o5 bu wo o5 shi go o1 ki tsu be jo ge gu ne wo ra o0 a21.44992423057556 ko ko be wo jo chi o2 bo no o4 pe pe ko bo ho ne mi6 go o5 o2 go be o5 go na na mi na go bu e ta6 wo mi e na be ya go shi ke ba ge wo tsu ma wo ba gu sa mu gu ge o0 ke o1 a6.59570574760437 o4 ko ne go ge o0 go o2 ya o1 o2 bu ki ki do e ko ki ge e ra go ra ka ki e a14 go o5 go ji hi ki ge be ro3 ja bo ni ko mi pa mi4 o5 chi bo wo ra ge ki mi5 ba mi mo mo ha ni no bo ko zu11 mi ya bo na be to ne ne ge bu go shi ra ki mi4 bu po ha no ki zu gu ra ta hi nu chi ta10 shi mi4 go ri jo na ge hi tsu ra a23.83755087852478 ge mu mi gu ha o5 ha o0 ko bo ba o4 po go ge ko no ho ta1 pa a22.324371337890625 pa ri mi o3 jo a21.1857248544693 ba mo go do go nu chi ja pe ra ri u gu ko ke bu hi ya ya ki ji ki hi ho go go gu go ha be ta6 na ki e po shi a18.548187732696533 go mi5 tsu ya ne na ji ja ri ba gu mo a22.324371337890625 ge go o0 ge ki ki a18.548187732696533 u yu ya wo o5 o5 ja no ku ya go mi hu pa go shi ja pe go ge wa ki o5 po ya mi6 o0 ho ri go ho ra gu ki",
                 "97:ta1 mo ne gu ki bu po o5 ro3 mi nu o5 ra ko do ko jo i gu sa ya shi ro1 ge za ra be chi no mo go na ki i no ri o5 ko go hi ja ko o5 ro6 go ri hi o5 ho bu go ba bu mo ka ra do ja ri ra ke e ni e o0 pa bo wa ma go ra wa ha e ho ri ja go ku ke u tsu o0 ne ya ke ne na ba ki go be no e ge mi5 na shi o5 bo ge bo be do pe ri gu na a20.83129596710205 ko ya mi e ji ha ta3 a23.83755087852478 mi5 ro3 go ge a21 gu po go zu no no ta6 go ge gu mi ko wa bo po mo bu ne go ba ko wo o5 o1 jo wo mo pe mi wo ra o4 hi yu yu ge wo chi hu ha hi wo ri go do mi ku hi ra chi ge hi bu wo yu do be o2 po ge ki be ki ha ge a9.230958938598633 no ya chi o1 po go o2 gu ja shi e ta be mi mi5 zu bu bo e ta gu chi ko ki bo ne ya hi no chi pa po tsu ge jo ja wa o1 go hi ge bu o5 o5 o2 o2 shi gu pa a21.1857248544693 ya a21.44992423057556 ge na do mi6 o1 be jo ge ne o5 ha a22.324371337890625 bo na mi6 o4 ko ge ya gu be go ji o2 mi ko ko mo a22.324371337890625 u ro3 go go ya o1 ra go ko mo o0 go ke bu a18.548187732696533 ki ja to chi mi4 mi go hu chi wo ja ra a23.83755087852478 to to o4 ki ke a18.548187732696533 hi ta3 shi wo tsu ha na mo ri be bu bo ja go hi gu hi be ka e mi5 ki pe jo go go to gu o5 e ha u po shi mi5 a22.324371337890625 ge ne o1 wo go ge go ge ta10 go no pe o5 o0 ki ke ra zu14 ma ki tsu no ba na o2 ra ne bu hi ba he pa ji ri ja za o2 ki ha ni ro3 mu ki o2 ki ri ho go go u ho no ki to bu bu ki ko ri no ko ki ke jo a6.59570574760437 o2 ki ge o5 ri ka ka gu a22.324371337890625 he mo",
                 "97:hi ta8 shi wo tsu ha ro5 na mo ri bu bu hi go ja hi gu hi bu ka e mi3 pe ki jo go go to gu o3 e ha po u shi mi3 a22.324371337890625 ge bo ne o1 wo go ge go ge ta6 go no o5 o0 ki ke yu zu14 ma ki tsu no no ba na o1 ra ne jo bu hi ba he pa ji ri ja za o2 he ha ki ni ro3 mu ki ki ri ho go go u u pe no ki ki to bu bu ki ko ri no ko hu ki ke jo a4.03422737121582 ki o2 ge o5 ri ka ho ka gu a22.324371337890625 he mo shi i ri e ge shi hi yu",
                 "97:hi hu shi wo tsu ha ro9 na o2 o2 bu bu hi go ja hi gu bu ka ko pe ki jo go go to gu o3 ya po ha u shi a22.324371337890625 bo ko wo ge go go ge ta10 go no o5 o0 o0 ki ke yu zu14 no ki ki tsu no no ba na o0 ra ne be jo hi ba he pa ji za ja mi o1 ha ki ni ro3 mu mi4 ki ki ri u ho mo go go u u pe wa no hi na ki to bu bu ki ko ri no zu ta13 ko ki i ke jo ki ki tsu no no ba na o0 ra ne be jo hi ba he pa ji za ja mi o1 ha po ni ro3 mu ki ki ri ho a7 go go u u pe wa no hi na ki to bu bu ki ko ri no zu ko ta13 ki i ke jo a5.125791072845459 ki o2 ge a7 o5 ri ka ya ki to be bu ki ko ri ko ta13 ki ke jo a5.125791072845459 ki no ge a7 o5 ri ka ka gu a19.963019847869873 he mo mo shi i ri e ge shi hi ga yu bo na mi ha po ki nu ro3 mu ki ri ho go u go u pe no ki ki to bu bu ki ko o4 no ko hu ki ke ke mi3 a5.125791072845459 ki o2 ge a7 o5 ri ka ho ka gu ko he mo shi i ri e ge shi hi hi yu ga bo na sa sa ga ku ri chi zu",
                 "97:ta7 hi tsu ji ma ya do chi pa no o0 ya sa ga ku mo mi shi ki no ko ne go bo ne su bo ki ta o1 o1 be ba go go wo o3 a24 gu o5 be ge go wo go go wo o3 a24 gu o5 be ge go wo ba ki shi ya ra ne go ta bo bu ne yu ki o0 be go go wo o4 gu o5 be go go ba ho ki ji ke hi go e mi5 ho na ji a23 hi wa ke go e ho na ma ya ki u ri do bo pe ka",
                 "97:ka u mi2 to tsu u ka pa bo na ki a8 ri go go be ki ne ki ho jo a7 gu ri bu o5 ki ge ge ke ba go ho o5 ki o2 po ki ha pe ri ki ko na ro3 ki tsu tsu ne pe u go o2 ya ji gu na ri go bu no u na ko bu sa ke ri a16 jo ki ha o2 to ge no ko o5 ko nu ji hi ha ki no hu ge gu a19.963019847869873 mu ge na ho be bo o1 hi i bu ri ri wo ko ho go o3 no ge go ko ga bu yu go chi u o3 no o0 go jo zu14 go be ko ri no o1 ni mi ka yu o4 ri ki to u ki go ka no ki to jo a9.718823194503784 no ge go ki ha ro3 ge ri ni ki bu ki ba wo ki ta13 he o2 to shi yu mu bu ke wo ge za a5 hi a5.125791072845459 zu jo ki o0 no ki shi ra me he pe to e ne a14.550715446472168 mo ba ke ha ko go jo za shi go no mi po jo he wo ro3 mo shi bu jo go i o3 ka ro4 ji bu ki mo ya ki ki to o0 bu pa ko go e no ha o3 sa a7 sa ha a7 i ke mi zu o0 ha bu za bo ki ro9 ho shi ri u ge pe na go ki mo sa mu no o0 ki zu3 i ya mi4 ki hi ri e ki hu u ki ne bu ja shi na wo no mi bo o2 ki ke ni no ji ko ki no he o0 ke na hi no ne go shi zu14 no mu be ki zu ki ki ra na u bo wa na mi3 wa bu go ka hi mi ka ba ko na u hi wa ki ba hi o2 ko a5.125791072845459 tsu hi i ba ya o5 ni shi go yu bo ra bu hi he a20.799158811569214 go ta13 pe o5 ta13 gu no ra ka o0 na chi o0 hi ri ri ji o1 no mu be u ko ke hi ri bu gu ki ga pa za gu po ba ka zu u ja to ji ri jo no pe a4.551114916801453 no o0 za na",
                 "97:na he chi ge ja o0 a2.1513171195983887 o0 ka ro2 gu jo to hi ba no go gu na hi ke za be o5 mu no ka ka a15.712974071502686 ta13 ji o1 ri zu o0 ba po a12 ri pe ji no ge ko no ri no ga pa hi u za ri ta13 e no",
                 "97:o1 ko be ka hi za po ri ke no o0 na u mu no o0 chi ri no go jo o0 za a15.712974071502686 e a12 ji no no ga gu pe pa ka he ri to a2.1513171195983887 o5 ba ja hi ro2 zu ba ge ka gu ta13 ge ta13 ji no hi ri na",
                 "97:o1 ko be hi ni za ri po ke no o0 na hi u mu no o0 chi no ri no go ba jo o0 za a15.712974071502686 e a12 ji no no ga gu pe pa ka he ri to a4.250659704208374 o5 ba ja hi ro2 zu ba ge ka gu ta16 bu ya ta13 ji no hi ri na",
                 "97:o1 ko bu hi ho ni za ba me ke no o0 hi no u mu he o0 chi no ri no go ba jo o0 za a15.712974071502686 e a12 ji gi no no ga ga gu pe pa ka he to a5.506158232688904 shi o5 ba ro2 ja zu ba ge ka gu ta16 bu ya ta13 ji no hi bo ri na ka wa o4 tsu hu",
                 "97:ta ko do bu hi ni ho za ba me ke tsu o1 ri no u mu he o0 chi no ri no go go ya jo o0 za a29.548563480377197 nu a12 ji gi no no ga gu pe mi8 pa ka he to a10.377212047576904 shi o5 ba ro2 ja zu zu ba ge gu ta16 ta16 be ya ta13 ji o1 hi bo ri na o0 ka wa o4 tsu hu be ki he ya o0 zu do mi7 ki",
                 "97:tsu ta8 bo o0 ni ni ke tsu wa shi o1 bu ri pa ya gi do ro2 he no ko me ho go me hu hu zu9 za ho ri to ji no ge a30.58281719684601 bu be hi ba bo ho o1 gu za go ho o4 jo u ga be ge chi me he ya mi7 nu ta19 ba o1 ki ga ki zu3 a12 ro4 gu ri ji he o2 za do ta15 na me ta12 hi mi8 he mi8 o5 chi na he ni ya ta ji ge na no zu3 tsu u gu u mi7 mi7 po o0 o0 be no ba ji go hu ki to ro13 za ri o5 do ta16 na ta12 mi8 he mi8 o5 chi na he nu ya ta ji ge na no zu3 tsu u no gu u mi7 mi7 mi2 po o0 o0 bu no ba ji go hu ki to ro13 za ri o5 ki a10.377212047576904 no ba go ki bo ko ra u na ku tsu mi no mu go to e gi bu mi7",
                 "97:tsu ta8 bo o1 ni ni go ke tsu wa shi o1 bu ri pa ya gi do ro7 he no ko me ho go me ge hu hu zu17 za ho mo ri to ji ge no a30.58281719684601 bu bu hi ba bo ho o1 gu za go ho o4 go ge hu hu zu17 za ho mo ri to ki ge no bu bu hi ba bo ho o1 gu za go ho o4 jo u ga be chi me he ya mi7 nu ta19 ba o1 ki ri pa ya gi do ro2 he no ba me ho go me wa hu hu zu9 za ho ri to ji no ge a37.30766201019287 bu be hi ba bo o1 gu za go ho o4 jo u ga ja jo ge chi me ya ha mi7 o2 ta19 ba shi ki ne ga ki ki zu3 a12 ro4 gu ri ji he o2 za do ta15 na me ta12 mi8 hi he o5 chi na he ni ya ta ji ko ge zu2 no ji tsu u gu ge u mi7 mi7 po o0 o0 be no ba ji go hu yu ki sa to ro13 za ri o5 do ta16 ta12 mi8 he mi8 o5 chi na he nu ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki ni a10.377212047576904 no ba go ge ki bo bu hi ba bo ho o1 gu za go ho o4 jo u be ga chi me he ya mi6 nu ta19 ba o1 ki ri pa ya gi do ro2 he no ba me ho go me wa hu hu zu9 za ho zu to ji no ge a30.58281719684601 bu be hi ba bo o1 gu za go ho o4 jo u ga ja jo ge chi me he ya mi7 nu ta19 ba shi ga ki zu3 a12 ro4 gu ri ji he ge za do ta15 na me ta12 mi8 mi8 hi he o5 chi na he ni ya ta4 ji ko ge no zu3 tsu u gu mi7 mi7 po o0 o0 be no ba ji go hu ki sa to ro13 za ri o5 ta16 ta12 he mi8 mi8 o5 chi na he ni ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o1 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki a10.377212047576904 ba o1 gu za go ho o4 go ge hu hu zu17 za ho mo ri to ki ji ge no bu bu hi ba bo ho o1 gu za go ho o4 jo u ga bu be chi me he ya mi7 nu ta19 ba o1 ki ri pa ya gi do ro2 he no ba me ho go me wa hu hu zu9 za ho ri to ji no ge a37.30766201019287 bu be hi ba bo o1 gu za go ho o4 jo u ga ja jo ge chi me ya ha mi7 o2 ta19 ba shi ki ne ga ki ki zu3 a12 ro4 gu ri ji he o2 za do ta15 na me ta12 mi8 hi he o5 chi na he ni ya ta ji ko ge zu2 no ji tsu u gu ge u mi7 mi7 po o0 o0 be no ba ji go hu yu ki sa to ro13 za ri o5 do ta16 ta12 mi8 he mi8 o5 chi na he nu ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki ni a10.377212047576904 no ba go ge ki bo bu hi ba bo ho o1 gu za go ho o4 jo u be ga chi me he ya mi6 nu ta19 ba o1 ki ri pa ya gi do ro2 he no ba me ho go me wa hu hu zu9 za ho zu to ji no ge a30.58281719684601 bu be hi ba bo o1 gu za go ho o4 jo u ga ja jo ge chi me he ya mi7 nu ta19 ba shi ga ki zu3 a12 ro4 gu ri ji he ge za do ta15 na me ta12 mi8 mi8 hi he o5 chi na he ni ya ta4 ji ko ge no zu3 tsu u gu mi7 mi7 no po o0 o0 be no ba ji go hu ki sa to ro13 za ri o5 ta16 ta12 mi8 he mi8 o5 chi na he ni ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki a10.377212047576904 ba no go ge ki bo ko ra ho be no ba ji go hu yu ki sa to ro9 za ri o5 do ta16 ta12 mi8 he mi8 chi na he nu ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki a10.377212047576904 no ba go ge ki bo bu hi ba bo ho o1 gu za go ho o4 jo u be bo chi me he ya mi6 nu ta19 ba o1 ki ri pa ya gi do ro2 he no ba me ho me wa hu hu zu9 za ho zu to ji ki no ge a30.58281719684601 bu be hi bo ba o1 gu za go ho o4 jo u ga ja jo ge chi me he ya mi7 nu ta19 ba shi ga ki zu3 a12 ro4 gu ri ji he ge za do na me ta12 mi8 mi8 hi he o5 chi na he nu ya ta ji ko ge no zu3 tsu u gu mi7 mi7 po o0 o0 be no ba ji go hu ki sa to ro13 za ri o5 ta16 ta12 he mi8 o5 chi na he ni ya ji ta ge na no zu3 tsu u u gu u no mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri o5 ki a10.377212047576904 ba no go ge ki bo ko ra ho zu to ji no ge a30.58281719684601 bu be hi ba bo o1 gu za go ho o4 jo u ga ja jo ge chi me he mi7 nu ta19 ba shi ki ga ki zu3 a12 ro4 gu ri ji he ge za do ta15 na me ta12 mi8 mi8 hi he o5 chi na he ni ya ta ji ko no zu3 tsu u gu mi7 mi7 po o0 o0 be no ba ji go hu ki sa to ro13 za ri o5 do ta16 ta12 mi8 he mi8 o5 chi na ni he ya ji ta ge na no zu3 tsu u u gu no u mi7 mi7 mi2 po o0 o0 bu no ba go ji o3 ki to ro13 a14 ri ki a10.377212047576904 ba no go ge ki bo ko ra u na ku tsu mi no mu go to e gi bu mi7 ki hu mi4 za za hi ya no",
                 "97:ho ya gu chi o1 ho ri ki u e ki ba za u ya ga gu ni ba no to za ho be hu go ho pa gu ge ba no to za ho be hu go ho pa bo go o5 zu gi o5 a6.89293909072876 go pa ge ki ni ki ge o2 ba ke o2 na be a30.58281719684601 no bu u u wa he bo bo ho o3 be ta19 za ge he gu o1 ge hu me no no no o4 ji ki go ki me jo ro1 hi hi be be a1 u do be me zu zu9 sa bu zu ke me",
                 "119:pe me ki nu ho ni tsu",
                 "119:gu a16.03079605102539 su a14.37553596496582 ki bo bo ko no jo",
                 "119:gu a16.03079605102539 su ki bo mi2 no jo wa wa",
                 "119:su ki mi2 jo wa wa",
                 "119:mi2 su ki nu jo jo wa bo ha ha",
                 "119:o1 gi bu o2 ki wa jo ki jo wa za ha bo e u na",
                 "119:o2 be gi do o2 ki wa hi jo wa ha bo to za e ma wa hi jo wa bo ma za e do wo hi u jo wa za ha e e u na u pe pe bo za ma e wa jo hi wa ha bo za ma e do u hi jo jo wa za ha e e bu u na ne ku pe pe ha he ki he",
                 "119:ne o2 bu gi do yu o2 ki wa hi jo ha bo to za e wa ma hi wa jo bo ma za e wo hi u wa wa ha e e hu u na bu mi1 pe pe bo za ma e wa jo hi ho wa wa ha bo bo ki gu ma do u hi jo jo za ha e ra i e bu u na ne ku pe pe ha he ki he no o0 ge pa mi2 u",
                 "138:wo go pa pa mo ri o1 hi hi ya ma nu ra",
                 "138:wo ho go mo pa ya ge ri mo hi o1 hi ya ma ni ra ri"
                ].collect {
                    [
                        incant(1,2,4,it).ca([150,150], ['black','blue','red','white'], init_random())
                    ].table(2)
                }.table(8, ['margin':30])
            )
        }
    ],
    [
        {
            prn ''
            prn title('Lessons learned'.ctr())
        },
        { pt '\n\t• We learned the basics of talking about cellular automata...' },
        { pt '\n\t\t→ States' },
        { pt '\n\t\t→ Update rules' },
        { pt '\n\t\t→ Neighborhoods' },
        { pt '\n\t\t→ Initial conditions' },
        { pt '\n\t\t→ Mapping numeric values to colors' },
        { pt '\n\t• Simple rules can produce remarkably complex phenomena' },
        { pt '\n\t• After exploring neighborhood sizes and colors, what\'s next?' },
        { pt '\n\t\t→ It\'s time to move to the next dimension...' }
    ],
    [
        {
            prn ''
            prn(incant(2,1,2,'77:ki su nu o4 ya ha mi ka ka')
                .ca([200,200], ['black','white'], init_single()).size(200,200,1,92).scaled(2))
            prn ''
            prn title('Entering Flatland'.ctr())
            prn ''
            pbt('Two-dimensional automata'.ctr())
        }
    ],
    [
        {
            prn ''
            prn title('Two-dimensional state'.ctr())
        },
        {
            pt('''
            |\tRecall the very first definition of state that we ever saw, good old array `/S``
            
            |\t\t`cS = [0 1 0] ''')
        },
        {
            pt('''
            |\tNow, we will redefine `/S`` to be a two-dimensional array, i.e. a matrix
            `c
            |\t\t     0 0 0 
            |\t\tS =  0 1 0 
            |\t\t     0 0 0  ''')
        },
        {
            pt('''
            |\tEverything we have learned so far, like `/R(S) → S'``, still applies when `/S`` is a matrix.

            |\tHowever, each cell now has many more neighbors than before...

            |\t\t\t...and so the `/definition of neighborhood`` must change.''')
        }
    ],
    [
        {
            prn ''
            prn title('Neighborhood #2'.ctr())
        },
        {
            pt '''
            |\tHow can we extend neighborhoods into two dimensions?'''
        },
        {
            pt '''
            |\t→ [possible output values] `^[possible input values]`^[input states]
            '''
        },
        {
            pt '''|\t\t• von Neumann neighborhood: 4 neighbors in cardinal NSEW'''
        },
        {
            pt '''|\t\t• Moore neighborhood: 8 neighbors in cardinal plus kitty-corner'''
        },
        {
            pt '''
            |\tLet's assume a Moore neighborhood with 2 colors...
            '''
        },
        {
            pt '''\t\t→ 2`^2`^9`` → 2`^512`` → 10`^154`` automata'''
        }
    ],
    [
        {
            prn ''
            prn title('The Game of Life'.ctr())
        },
        // 100:ki mi a2 a3 u ki mi8 a3 ma ya ra
        {
            rule = incant(2,1,2,"100:ki mi a2 a3 u ki mi8 a3 ma ya ra")
            bnw = ['black','white']
            x=100; y=100
            //anim(
                //rule.ca([(int) x, (int) y], bnw, init_random()).frames(100)*.toJfxImage()
            //)
            prn([
                rule.ca([(int) x, (int) y], bnw, init_random()).animate(3f,-1,100),
                txts('''
                |\tLife is a Class 4 (complex) 2D, 2-color automata with Moore neighborhood

                |\tCells are either 'living' (white) or 'dead' (black)

                |\tPublished by John Conway in 1970 based on work
                |\tby John von Neumann and Stanislaw Ulam

                |\tIt has been studied extensively and is Turing complete

                |\tAnd its update rule is quite simple...
                ''')
            ].table(2))
        },
        {
            pt '''`c
            for c in S:
                let  a ← count of alive neighbors
                if   c is alive and a<2 then c' ← dead    // underpopulation
                elif c is alive and a>3 then c' ← dead    // overpopulation
                elif c is  dead and a=3 then c' ← alive   // reproduction
                else                         c' ← c       // statis
            '''
        }
    ],
    [
        {
            prn ''
            prn title('It\'s life... but not as we know it'.ctr())
        },
        {
            pt '''
            \tThe Life universe is home to many different species of creatures...'''
        },
        {
            pt '''
            \tThese creatures are classified according to their behavior. Let's look at a few.
            '''
        },
        {
            // still life
            rule = incant(2,1,2,"100:ki mi a2 a3 u ki mi8 a3 ma ya ra")
            bnw = ['black','white']
            x=20; y=20
            prn(
                [
                    [ 'Block',
                      '''
                         |11
                         |11'''.stripMargin()
                    ],
                    [ 'Beehive',
                      '''
                         |0110
                         |1001
                         |0110'''.stripMargin()
                    ],
                    [ 'Boat',
                      '''
                         |110
                         |101
                         |010'''.stripMargin()
                    ],
                    [ 'Loaf',
                      '''
                         |0110
                         |1001
                         |0101
                         |0010'''.stripMargin()
                    ],
                    [ 'Tub',
                      '''
                         |010
                         |101
                         |010'''.stripMargin()
                    ]
                ].collect { 
                    rule.ca([x, y], bnw, init_custom(it[1],x,y,-1,-1)).animate(6f,-1,70).label(it[0],['pos':'bottom'])
                }.table(5, ['margin':20,'padding':20]).label('Still Life', ['pos':'bottom']).ctr()
            )
        },
        {
            // oscillators
            rule = incant(2,1,2,"100:ki mi a2 a3 u ki mi8 a3 ma ya ra")
            bnw = ['black','white']
            x=20; y=20
            prn(
                [
                    [ 'Blinker',
                      '''
                         |111'''.stripMargin()
                    ],
                    [ 'Toad',
                      '''
                         |0111
                         |1110'''.stripMargin()
                    ],
                    [ 'Beacon',
                      '''
                         |1100
                         |1100
                         |0011
                         |0011'''.stripMargin()
                    ],
                    [ 'Pulsar',
                      '''
                         |0011100011100
                         |0000000000000
                         |1000010100001
                         |1000010100001
                         |1000010100001
                         |0011100011100
                         |0000000000000
                         |0011100011100
                         |1000010100001
                         |1000010100001
                         |1000010100001
                         |0000000000000
                         |0011100011100'''.stripMargin()
                    ],
                    [ 'Pentadecathlon',
                      '''
                         |010
                         |010
                         |101
                         |010
                         |010
                         |010
                         |010
                         |101
                         |010
                         |010'''.stripMargin()
                    ]
                ].collect { 
                    rule.ca([x, y], bnw, init_custom(it[1],x,y,-1,-1)).animate(6f,-1,70).label(it[0],['pos':'bottom'])
                }.table(5, ['margin':20]).label('\nOscillators', ['pos':'bottom']).ctr()
            )
        },
        {
            // spaceships
            rule = incant(2,1,2,"100:ki mi a2 a3 u ki mi8 a3 ma ya ra")
            bnw = ['black','white']
            x=20; y=20
            prn(
                [
                    [ '\nGlider',
                      '''
                         |010
                         |001
                         |111'''.stripMargin()
                    ],
                    [ 'Lightweight\nSpaceship',
                      '''
                         |10010
                         |00001
                         |10001
                         |01111'''.stripMargin()
                    ],
                    [ 'Middleweight\nSpaceship',
                      '''
                         |011111
                         |100001
                         |000001
                         |100010
                         |001000'''.stripMargin()
                    ],
                    [ 'Heavyweight\nSpaceship',
                      '''
                         |0111111
                         |1000001
                         |0000001
                         |1000010
                         |0011000'''.stripMargin()
                    ]
                ].collect { 
                    rule.ca([x, y], bnw, init_custom(it[1],x,y,-1,-1)).animate(6f,-1,70).label(it[0],['pos':'bottom'])
                }.table(4, ['margin':20]).label('\nSpaceships', ['pos':'bottom']).ctr()
            )
        }
    ],
    [
        {
            prn ''
            prn title('Slightly more complex lifeforms'.ctr())
        },
        {
            // gosper gun
            gosper = CA.fromFile('/Users/jkw/work/ca/src/2d-gosper-gun.ca','text')

            prn(
                [
                    [ 'Gosper glider gun - An oscillating spaceship factory', gosper ]
                ].collect { 
                    it[1].animate(3f,-1,70).label(it[0],['pos':'bottom'])
                }.table(5, ['margin':20,'padding':20]).label('', ['pos':'bottom']).ctr()
            )
        },
        {
            // spacerake
            spacerake = CA.fromFile('/Users/jkw/work/ca/src/2d-spacerake.ca','text')

            prn(
                [
                    [ 'Spacerake - A spaceship spaceship factory', spacerake ]
                ].collect { 
                    it[1].animate(3f,-1,70).label(it[0],['pos':'bottom'])
                }.table(5, ['margin':20,'padding':20]).label('', ['pos':'bottom']).ctr()
            )
        }
    ],
    [
        {
            prn ''
            prn title('I\'m not a clone, you\'re the clone!'.ctr())
        },
        {
            pt '''
            |\t`/Self-reproducing automata`` are structures that are able to make copies of themselves.

            |\t`/Langton loops`` (Christopher Langton, 1984) use a shealth to protect their DNA,
            |\twhich is replicated in the cloned loop.
            '''
        },
        {
            langton = CA.fromFile('/Users/jkw/work/ca/src/langton2.ca','text')
            prn(langton.animate(4f,-1,70).label('').ctr())
        }
    ],
    [
        {
            boscos = CA.fromFile('/Users/jkw/work/ca/src/larger_than_life_s5_boscos_rule.ca','text')
            prn ''
            prn(boscos.frame(25).toJfxImage())
            //prn(incant(2,1,2,'77:ki su nu o4 ya ha mi ka ka')
                //.ca([200,200], ['black','white'], init_single()).size(200,200,1,92).scaled(2))
            prn ''
            prn title('Practical Applications'.ctr())
            //prn ''
            //pbt('Practical applications'.ctr())
        }
    ],
    [
        {
            prn ''
            prn title('The Hodgepodge Machine'.ctr())
        },
        {
            pt '''
            |\tDeveloped to model complex chemical reactions like CO`v2`` formation

            |\tEmulates reaction-diffusion partial differential equations

            |\t(Gerhardt & Schuster, 1989)
            '''
        },
        {
            hodge = CA.fromFile('/Users/jkw/work/ca/src/hodgepodge-hc-red.ca','text')
            //prn(hodge.animate(4f,100,70).label('').ctr())
            prn(
                [
                    hodge.size(200,200).scaled(2),
                    img('file:///Users/jkw/Pictures/patterns/bz1.jpg')
                ].table(2).label('Belousov-Zhabotinsky Reaction', ['pos':'bottom']).ctr()
            )
        }
    ],
    [
        {
            prn ''
            prn title('Slime Molds'.ctr())
        },
        {
            pt '''
            \tEvery presentation should have one.
            '''
        },
    ],
    {
        prn('')
        prn(h2('Whence?'.ctr()))
        pause()
        pc('\t=> Los Alamos')
        pc('\t=> John von Neumann, Stanislaw Ulam')
        pc("\t=> 1950s -> Conway's Game of Life")
        pc('\t=> AKA cellular spaces, tesselations, iterative arrays')
        pc('\t=> A rule for updating cell states (!)')
    },
    {
        r129 = rules(1,1,2).rule(129)
        bnw = ['white','black']

        x=600; y=300
        anim(
            (10..1).collect { r129.ca([(int) (x/it), (int) (y/it)], bnw, init_single()).scaled(it) }
        )
    },
    {
        prn('Scales'.ctr().style('h1'))
        prn('Scale is measured by colors, dimensions, and neighbors')
        prn('Possible input values: (2*neighbors+1) ^ dims')
        prn('Possible output values: colors ^ inputs => colors ^ ((2*neighbors+1) ^ dims')
        prn('Possible rules: colors ^ outputs => colors ^ (colors ^ inputs) => colors ^ (colors ^ ((2*neighbors+1) ^ dims))')
        pause()
        prn('')
        prn('Universe: One dimension & nearest neighbor & two colors (1,1,2)'.style('h2'))
        prn('Possible rules:')
        pause()
        prn('2 ^ (2 ^ ((2*1+1) ^ 1)) => 2 ^ (2 ^ 3) => 2 ^ 8 => 256'.ctr())
        pause()
        prn('')
        prn('Universe: Two dimensions & nearest neighbor & two colors (2,1,2)'.style('h2'))
        prn('Possible rules:')
        pause()
        prn('2 ^ (2 ^ ((2*1+1) ^ 2)) => 2 ^ (2 ^ 9) => 2 ^ 512 => ~10^154'.ctr())
        pause()
        prn('')
        prn('Universe: Two dimensions & nearest neighbor & three colors (2,1,3)'.style('h2'))
        prn('Possible rules:')
        pause()
        prn('3 ^ (3 ^ ((2*1+1) ^ 2)) => 3 ^ (3 ^ 9) => 3 ^ 19683 => ~10^9391'.ctr())
    },
    {
        prn('One-Dimensional Rules'.ctr().style('h1'))
        prn('One dimension, two colors; part 1')
        prn((0..127).collect { rules(1,1,2).rule(it).ca([40,40], bnw, init_single()).label("Rule ${it}", ['pos':'bottom']) }.table(16).ctr())
    },
    {
        prn('One-Dimensional Rules'.ctr().style('h1'))
        prn('One dimension, two colors; part 2')
        prn((128..255).collect { rules(1,1,2).rule(it).ca([40,40], bnw, init_single()).label("Rule ${it}", ['pos':'bottom']) }.table(16).ctr())
    },
    {
        prn h1('Sources').ctr()
        prn 'Cellular Automata: A Discrete View of the World (Joel L. Schiff)'
        prn 'The Nonlinear Workbook, 6th ed (Steeb)'
        prn 'Designing Beauty: The Art of Cellular Automata (Adamatzky)'
        prn 'Probabilistic Cellular Automata (Louis/Nardi)'
        prn 'A New Kind of Science (Wolfram)'
    }
]


def g_idx = 0;

restart = { s -> g_idx = 0; start(s) }

slides_tof = {}


start = { slides, s=g_idx ->
    while(s<slides.size()) {
        System.err.println("displaying slide $s");
        //prn('clear')
        clear()
        //prn('slide')
        if(slides[s] instanceof List) {
            i=0
            maxi=0
            while(i<slides[s].size()) {
                if(maxi==i) {
                    slides[s][i]()
                }
                key = getch()
                if(key=='space'||key=='down') {
                    i++
                    maxi = i
                }
                else if(key=='up') {
                    $c.unprint()
                    i--;
                }
                else if(key=='escape') {
                    s = slides.size();
                    break;
                }
                else {
                    maxi=-1;
                }
                if(i==-1) {
                    s--;
                    break;
                }
                else if(i==slides[s].size()) {
                    s++;
                    break;
                }
            }
        }
        else {
            slides[s]()
            key = getch()
            //prn('key')
            System.err.println("KEY: '${key}'")
            if(key=='space'||key=='down') {
                s++
            }
            else if(key=='up' && s>0) {
                s--;
            }
            else if(key=='escape') {
                break;
            }
        }
        /*prn("rep: ${key}")*/
    }
}

//start(s2)
//start(slides)

//web("https://docs.oracle.com/javase/8/javafx/embedded-browser-tutorial/add-browser.htm")
