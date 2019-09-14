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
            pbt '''
            |\tGiven a state 'S' and an update rule 'R', then:
            
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

            |\t→ [possible output values] `^[input states]`^[possible input values]
            '''
        },
        {
            pt '\t→ 2`^2`^3`` → 2`^8`` → 256 elementary automata'
        },
        {
            pt '''
            |\tEach rule can be referred to in a kind of Gray code.

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
            pt '\n\t\t→ Initial conditions are important.'
        }
    ],
    [
        {
            //77:ki su nu o4 ya ha mi ka ka
            prn ''
            prn(incant(2,1,2,'77:ki su nu o4 ya ha mi ka ka')
                .ca([200,200], ['black','white'], init_single()).size(200,200,1,92).scaled(2))
            prn ''
            prn title('Entering Flatland'.ctr())
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
        }
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
        prn ''
        prn h1('The Game of Life')
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
