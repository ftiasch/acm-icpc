import java.util.*;
import java.math.*;

public class GreedyTravelingSalesman {
    int solve(int[][] graph) {
        int n = graph.length;
        boolean[] visit = new boolean[n];
        int ret = 0;
        for (int i = 1, u = 0; i < n; ++ i) {
            visit[u] = true;
            int next = -1;
            for (int v = 0; v < n; ++ v) {
                if (!visit[v] && (next == -1 || graph[u][v] < graph[u][next])) {
                    next = v;
                }
            }
            ret += graph[u][next];
            u = next;
        }
        return ret;
    }

    public int worstDistance(String[] thousands, String[] hundreds, String[] tens, String[] ones) {		
        int n = thousands.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++ i) {
            for (int j = 0; j < n; ++ j) {
                graph[i][j] = Integer.parseInt("" + thousands[i].charAt(j) + hundreds[i].charAt(j) + tens[i].charAt(j) + ones[i].charAt(j));
            }
        }
        int answer = 0;
        for (int i = 0; i < n; ++ i) {
            ArrayList <Integer> choices = new ArrayList <Integer>();
            choices.add(1);
            choices.add(9999);
            for (int j = 0; j < n; ++ j) {
                for (int d = -1; d <= 1; ++ d) {
                    int length = graph[i][j] + d;
                    if (1 <= length && length <= 9999) {
                        choices.add(length);
                    }
                }
            }
            for (int j = 0; j < n; ++ j) {
                if (i != j) {
                    int backup = graph[i][j];
                    for (int choice : choices) {
                        graph[i][j] = choice;
                        answer = Math.max(answer, solve(graph));
                    }
                    graph[i][j] = backup;
                }
            }
        }
        return answer;
    }

// BEGIN CUT HERE
    public static void main(String[] args) {
		if (args.length == 0) {
			GreedyTravelingSalesmanHarness.run_test(-1);
		} else {
			for (int i=0; i<args.length; ++i)
				GreedyTravelingSalesmanHarness.run_test(Integer.valueOf(args[i]));
		}
	}
// END CUT HERE
}

// BEGIN CUT HERE
class GreedyTravelingSalesmanHarness {
	public static void run_test(int casenum) {
		if (casenum != -1) {
			if (runTestCase(casenum) == -1)
				System.err.println("Illegal input! Test case " + casenum + " does not exist.");
			return;
		}
		
		int correct = 0, total = 0;
		for (int i=0;; ++i) {
			int x = runTestCase(i);
			if (x == -1) {
				if (i >= 100) break;
				continue;
			}
			correct += x;
			++total;
		}
		
		if (total == 0) {
			System.err.println("No test cases run.");
		} else if (correct < total) {
			System.err.println("Some cases FAILED (passed " + correct + " of " + total + ").");
		} else {
			System.err.println("All " + total + " tests passed!");
		}
	}
	
	static boolean compareOutput(int expected, int result) { return expected == result; }
	static String formatResult(int res) {
		return String.format("%d", res);
	}
	
	static int verifyCase(int casenum, int expected, int received) { 
		System.err.print("Example " + casenum + "... ");
		if (compareOutput(expected, received)) {
			System.err.println("PASSED");
			return 1;
		} else {
			System.err.println("FAILED");
			System.err.println("    Expected: " + formatResult(expected)); 
			System.err.println("    Received: " + formatResult(received)); 
			return 0;
		}
	}

	static int runTestCase(int casenum__) {
		switch(casenum__) {
		case 0: {
			String[] thousands        = {"055", "505", "550"};
			String[] hundreds         = {"000", "000", "000"};
			String[] tens             = {"000", "000", "000"};
			String[] ones             = {"000", "000", "000"};
			int expected__            = 14999;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 1: {
			String[] thousands        = {"018", "101", "990"};
			String[] hundreds         = {"000", "000", "990"};
			String[] tens             = {"000", "000", "990"};
			String[] ones             = {"000", "000", "990"};
			int expected__            = 17999;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 2: {
			String[] thousands        = {"00888", "00999", "00099", "00009", "00000"}
;
			String[] hundreds         = {"00000", "00999", "00099", "00009", "00000"}
;
			String[] tens             = {"00000", "10999", "11099", "11109", "11110"}
;
			String[] ones             = {"01000", "00999", "00099", "00009", "00000"}
;
			int expected__            = 37997;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 3: {
			String[] thousands        = {"000000", "000000", "990999", "999099", "999909", "999990"};
			String[] hundreds         = {"000000", "000000", "990999", "999099", "999909", "999990"};
			String[] tens             = {"000000", "000000", "990999", "999099", "999909", "999990"};
			String[] ones             = {"011111", "101111", "990998", "999099", "999809", "999980"};
			int expected__            = 39994;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 4: {
			String[] thousands        = {"00", "00"};
			String[] hundreds         = {"00", "00"};
			String[] tens             = {"00", "00"};
			String[] ones             = {"01", "10"};
			int expected__            = 9999;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 5: {
			String[] thousands        = {"0930", "1064", "0104", "1070"};
			String[] hundreds         = {"0523", "1062", "6305", "0810"};
			String[] tens             = {"0913", "0087", "3109", "1500"};
			String[] ones             = {"0988", "2030", "6103", "5530"};
			int expected__            = 14124;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 6: {
			String[] thousands        = {"0329", "2036", "2502", "8970"};
			String[] hundreds         = {"0860", "5007", "0404", "2770"};
			String[] tens             = {"0111", "2087", "2009", "2670"};
			String[] ones             = {"0644", "1094", "7703", "7550"};
			int expected__            = 17785;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}
		case 7: {
			String[] thousands        = {"098444156277392825243100607342", "200097656837707947798866622385",
"290231695687128834848596019656", "407026565077650435591867333275",
"843401002617957470339040852874", "349970591997218853400632158696",
"419933000593511123878416328483", "696294503254214847884399055978",
"641473980706392541888675375279", "936720077098054565078142449625",
"203476089500970673371115103717", "511071853860312304204656816567",
"347105714685052402147763392257", "125122220860203856679947732062",
"121462979669220132944063071653", "928254504048223043681383050365",
"502607124708785202536036594373", "793596587517012870906900400361",
"712360060935346182084840996318", "761671392040312345002273366240",
"812935320276738878200716148806", "228506917464479046839069740872",
"755395721473881083093906155745", "192597782177910118061710039501",
"721382839206745793530453013267", "076061794267810491768114700256",
"857528357758085424372388710251", "173322450800442594145600093043",
"761667192345925280210514410800", "521229810525064090301842864060"};
			String[] hundreds         = {"098270581534726237580246464451", "108829763716747148395013332067",
"830061279541380758964430491222", "431005058477371114834129826284",
"601807314489142917339949914290", "330640126699733151822328009407",
"851821069798846354566780680271", "648888407535627630663351884365",
"051398660825518466890170447894", "631934884097214069747147155777",
"768071219404930950472885304916", "924954163330715847561718395488",
"189910033179029204426829479070", "960645776060701332402794474433",
"244875842263950931884758650019", "528470075229660077692189442311",
"752198673046476808978058423064", "899325998610605600525587569431",
"965750123741820904031880230236", "121658852172052167706008445728",
"556199378085507717770434101126", "864838242791403197110088834005",
"593435343245223500439707230479", "622529771475840345624500421425",
"503486612623475041392122088159", "518334626169655694269507400008",
"967091631529233593414345370288", "300474162107271438029222620086",
"010527691044447729596127150108", "742822904991333205419603623270"};
			String[] tens             = {"029421809872798033258029765788", "705135039569772524273274786652",
"170567418260893393020344098265", "401043354947659563658581268242",
"746709065616595245635350557925", "739570024549618413776557843034",
"184597012262496958610853505745", "689811400727818703807051112784", 
"894453010121164288965541305235", "323145029073008946088869964941", 
"834269564400729646453274750586", "538976762970745472202055589093", 
"765511399939087047106252621388", "906733295711605356366138410423", 
"107653940551700559321642810836", "428402693021051075533830345295", 
"386782660475155103347385287948", "936626025836194580089064628716", 
"718522629491464055045890912121", "370656945845300237607868352243", 
"951908186614186444840337711498", "535178875249889835014025850038", 
"505970047705717604298603983975", "484398304612602344941130624527", 
"048342694079170795987835013947", "860331019262176299872846206272", 
"549663926438975145562538360932", "329735455392841851511474791078", 
"711755200061373546828858448100", "778808866574640894148527759780"};
			String[] ones             = {"050738147930236727719964251439", "804492562859282318664226330103", 
"610197568193830684654773608216", "279000416545607314567843085541", 
"782201171759873927350740022455", "043370803444176631019883186675", 
"566092086050401228622782761449", "469598907881602996036692882305", 
"116923500417992303845370254124", "796876115092839169954790509461", 
"783836410405270687557924090071", "095144151150833738671751747749", 
"354474585664039135189964700948", "328968176148004939648962631420", 
"829651915384290848347221555092", "170980383407813034573738951375", 
"728655435703349509419725538350", "121896684176286430427852435647", 
"315710894574884960021671476788", "592177839598531202003634382961", 
"876587919610157913350259498196", "505517243779897451333006271744", 
"618607877753891664471800511372", "826358757330233811836040764274", 
"206641252044293046424432092833", "704519364781672964993499009545", 
"624793571592392775564426440338", "571938479010503551295729304078", 
"077967252884369103891335711508", "870185204806328841827105139840"};
			int expected__            = 39896;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}

		// custom cases

/*      case 8: {
			String[] thousands        = ;
			String[] hundreds         = ;
			String[] tens             = ;
			String[] ones             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}*/
/*      case 9: {
			String[] thousands        = ;
			String[] hundreds         = ;
			String[] tens             = ;
			String[] ones             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}*/
/*      case 10: {
			String[] thousands        = ;
			String[] hundreds         = ;
			String[] tens             = ;
			String[] ones             = ;
			int expected__            = ;

			return verifyCase(casenum__, expected__, new GreedyTravelingSalesman().worstDistance(thousands, hundreds, tens, ones));
		}*/
		default:
			return -1;
		}
	}
}

// END CUT HERE
