<?xml version="1.0" encoding="UTF-8"?>
<xmi:XMI xmi:version="2.0" xmlns:xmi="http://www.omg.org/XMI" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:exercise="platform:/plugin/no.hal.learning.exercise.model/model/exercise.ecore" xmlns:jdt="platform:/plugin/no.hal.learning.exercise.jdt/model/jdt-exercise.ecore" xmlns:junit="platform:/plugin/no.hal.learning.exercise.junit/model/junit-exercise.ecore" xmlns:workbench="platform:/plugin/no.hal.learning.exercise.workbench/model/workbench-exercise.ecore">
  <exercise:Exercise>
    <parts xsi:type="exercise:ExercisePart" title="Java classes">
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Edit source code for Java classes."/>
        <a xsi:type="jdt:JdtSourceEditAnswer" resourcePath="/ovinger/src/*" className="*"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Launch Java classes."/>
        <a xsi:type="jdt:JdtLaunchAnswer" className="*"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Run JUnit tests."/>
        <a xsi:type="junit:JunitTestAnswer" testRunName="*"/>
      </tasks>
    </parts>
    <parts xsi:type="exercise:ExercisePart" title="Tool usage">
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Handle debug events."/>
        <a xsi:type="workbench:DebugEventAnswer" elementId="*" action="*.*"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use debug commands"/>
        <a xsi:type="workbench:CommandExecutionAnswer" elementId="*" action="executeSuccess"/>
      </tasks>
      <tasks xsi:type="exercise:Task">
        <q xsi:type="exercise:StringQuestion" question="Use editors and views"/>
        <a xsi:type="workbench:PartTaskAnswer" elementId="*" action="*"/>
      </tasks>
    </parts>
  </exercise:Exercise>
  <exercise:ExerciseProposals exercise="/0">
    <proposals exercisePart="/0/@parts.0">
      <proposals xsi:type="jdt:JdtSourceEditProposal" question="/0/@parts.0/@tasks.0/@q" answer="/0/@parts.0/@tasks.0/@a">
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237340268" resourcePath="/ovinger/src/stateandbehavior/AccountTest.java" sizeMeasure="183" className="stateandbehavior.AccountTest">
          <edit xsi:type="exercise:StringEdit" storedString="package stateandbehavior;&#xA;&#xA;import junit.framework.TestCase;&#xA;import no.hal.jex.runtime.JExercise;&#xA;import stateandbehavior.Account;&#xA;&#xA;@JExercise(description = &quot;Tests stateandbehavior.Account&quot;)&#xA;@SuppressWarnings(&quot;all&quot;)&#xA;public class AccountTest extends TestCase {&#xA;  private Account account;&#xA;  &#xA;  @Override&#xA;  protected void setUp() {&#xA;    account = new Account();&#xA;    &#xA;  }&#xA;  &#xA;  private boolean operator_equals(final double d1, final double d2) {&#xA;    boolean _xblockexpression = false;&#xA;    {&#xA;      final double epsilon = 0.000001d;&#xA;      _xblockexpression = (((d1 - epsilon) &lt; d2) &amp;&amp; ((d1 + epsilon) > d2));&#xA;    }&#xA;    return _xblockexpression;&#xA;  }&#xA;  &#xA;  @JExercise(description = &quot;&lt;h3>Konstrukt\u00F8r&lt;/h3>Tests \n\t\tinitialization\n&quot;)&#xA;  public void testConstructor() {&#xA;    _test__constructor_transitions0_effects0_state(account);&#xA;    &#xA;  }&#xA;  &#xA;  @JExercise(tests = &quot;void deposit(double)&quot;, description = &quot;&lt;h3>Innskudd&lt;/h3>Tests \n\t\tthe following sequence:\n\t\t&lt;ul>\n\t\t&lt;li>Setter inn 100 kr.: deposit(100)&lt;/li>\n\t\t&lt;/ul>\n&quot;)&#xA;  public void testDeposit() {&#xA;    _transition_exprAction__deposit_transitions0_actions0(account);&#xA;    _test__deposit_transitions0_effects0_state(account);&#xA;    &#xA;  }&#xA;  &#xA;  @JExercise(tests = &quot;void deposit(double)&quot;, description = &quot;&lt;h3>Negativt innskudd&lt;/h3>Tests \n\t\tthe following sequence:\n\t\t&lt;ul>\n\t\t&lt;li>Setter inn -50 kr.: deposit(-50)&lt;/li>\n\t\t&lt;/ul>\n&quot;)&#xA;  public void testDepositNegative() {&#xA;    _transition_exprAction__depositNegative_transitions0_actions0(account);&#xA;    _test__depositNegative_transitions0_effects0_state(account);&#xA;    &#xA;  }&#xA;  &#xA;  @JExercise(tests = &quot;double setInterestRate(double);void deposit(double);void addInterest()&quot;, description = &quot;&lt;h3>Legge til renter&lt;/h3>Tests \n\t\tthe following sequence:\n\t\t&lt;ul>\n\t\t&lt;li>Setter rentefoten: interestRate = 5&lt;/li>\n\t\t&lt;li>Setter inn 100 kr.: deposit(100)&lt;/li>\n\t\t&lt;li>addInterest&lt;/li>\n\t\t&lt;/ul>\n&quot;)&#xA;  public void testAddInterest() {&#xA;    _transition_exprAction__addInterest_transitions0_actions0(account);&#xA;    _test__addInterest_transitions0_effects0_state(account);&#xA;    _transition_exprAction__addInterest_transitions1_actions0(account);&#xA;    _test__addInterest_transitions1_effects0_state(account);&#xA;    _transition_exprAction__addInterest_transitions2_actions0(account);&#xA;    _test__addInterest_transitions2_effects0_state(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__constructor_transitions0_effects0_state(final Account it) {&#xA;    _test__constructor_transitions0_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__constructor_transitions0_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    assertTrue(&quot;balance == 0 failed&quot;, this.operator_equals(_balance, 0));&#xA;    &#xA;  }&#xA;  &#xA;  private void _transition_exprAction__deposit_transitions0_actions0(final Account it) {&#xA;    try {&#xA;      &#xA;      it.deposit(100);&#xA;      } catch (junit.framework.AssertionFailedError error) {&#xA;      fail(&quot;deposit(100) failed: &quot; + error.getMessage());&#xA;    }&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__deposit_transitions0_effects0_state(final Account it) {&#xA;    _test__deposit_transitions0_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__deposit_transitions0_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    assertTrue(&quot;balance == 100 failed after deposit(100)&quot;, this.operator_equals(_balance, 100));&#xA;    &#xA;  }&#xA;  &#xA;  private void _transition_exprAction__depositNegative_transitions0_actions0(final Account it) {&#xA;    try {&#xA;      &#xA;      it.deposit((-50));&#xA;      } catch (junit.framework.AssertionFailedError error) {&#xA;      fail(&quot;deposit(-50) failed: &quot; + error.getMessage());&#xA;    }&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__depositNegative_transitions0_effects0_state(final Account it) {&#xA;    _test__depositNegative_transitions0_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__depositNegative_transitions0_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    assertTrue(&quot;balance == 0 failed after deposit(-50)&quot;, this.operator_equals(_balance, 0));&#xA;    &#xA;  }&#xA;  &#xA;  private void _transition_exprAction__addInterest_transitions0_actions0(final Account it) {&#xA;    try {&#xA;      &#xA;      it.setInterestRate(5);&#xA;      } catch (junit.framework.AssertionFailedError error) {&#xA;      fail(&quot;interestRate = 5 failed: &quot; + error.getMessage());&#xA;    }&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions0_effects0_state(final Account it) {&#xA;    _test__addInterest_transitions0_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions0_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    boolean _equals = this.operator_equals(_balance, 0);&#xA;    assertTrue(&quot;balance == 0 failed after interestRate = 5&quot;, _equals);&#xA;    &#xA;    double _interestRate = it.getInterestRate();&#xA;    assertTrue(&quot;interestRate == 5 failed after interestRate = 5&quot;, this.operator_equals(_interestRate, 5));&#xA;    &#xA;  }&#xA;  &#xA;  private void _transition_exprAction__addInterest_transitions1_actions0(final Account it) {&#xA;    try {&#xA;      &#xA;      it.deposit(100);&#xA;      } catch (junit.framework.AssertionFailedError error) {&#xA;      fail(&quot;deposit(100) failed: &quot; + error.getMessage());&#xA;    }&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions1_effects0_state(final Account it) {&#xA;    _test__addInterest_transitions1_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions1_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    assertTrue(&quot;balance == 100 failed after deposit(100)&quot;, this.operator_equals(_balance, 100));&#xA;    &#xA;  }&#xA;  &#xA;  private void _transition_exprAction__addInterest_transitions2_actions0(final Account it) {&#xA;    try {&#xA;      &#xA;      it.addInterest();&#xA;      } catch (junit.framework.AssertionFailedError error) {&#xA;      fail(&quot;addInterest failed: &quot; + error.getMessage());&#xA;    }&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions2_effects0_state(final Account it) {&#xA;    _test__addInterest_transitions2_effects0_state_objectTests0_test(account);&#xA;    &#xA;  }&#xA;  &#xA;  private void _test__addInterest_transitions2_effects0_state_objectTests0_test(final Account it) {&#xA;    &#xA;    double _balance = it.getBalance();&#xA;    assertTrue(&quot;balance == 105 failed after addInterest&quot;, this.operator_equals(_balance, 105));&#xA;    &#xA;  }&#xA;}&#xA;"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237400834" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="5" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="public class Account {&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.0/@edit" start="27" end="-4"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237485124" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="7" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;&#xA;&#x9;private double balance;&#xA;&#x9;private double interest;" edit="/1/@proposals.0/@proposals.0/@attempts.1/@edit" start="50" end="-4"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237500441" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="13" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;&#xA;&#x9;public double getBalance() {&#xA;&#x9;&#x9;return balance;&#xA;&#x9;}&#xA;&#x9;&#xA;&#x9;&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.2/@edit" start="103" end="-3"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237516475" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="13" warningCount="1" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="Rate" edit="/1/@proposals.0/@proposals.0/@attempts.3/@edit" start="101" end="-62"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="93" charEnd="101" severity="1" problemCategory="120" problemType="570425421"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237601812" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="17" warningCount="1" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="public void deposit(double amount) {&#xA;&#x9;&#x9;if (amount > 0) {&#xA;&#x9;&#x9;&#x9;balance += amount;&#xA;&#x9;&#x9;}&#xA;&#x9;}" edit="/1/@proposals.0/@proposals.0/@attempts.4/@edit" start="163" end="-4"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="93" charEnd="101" severity="1" problemCategory="120" problemType="570425421"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237632924" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="25" warningCount="1" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;&#xA;&#x9;public double getInterestRate() {&#xA;&#x9;&#x9;return interestRate;&#xA;&#x9;}&#xA;&#x9;&#xA;&#x9;public void setInterestRate(double interestRate) {&#xA;&#x9;&#x9;this.interestRate = interestRate;&#xA;&#x9;}&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.5/@edit" start="249" end="-3"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="93" charEnd="101" severity="1" problemCategory="120" problemType="570425421"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237641646" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="26" warningCount="1" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.6/@edit" start="405" end="-3"/>
          <markers xsi:type="jdt:JdtMarkerInfo" lineNumber="6" charStart="93" charEnd="101" severity="1" problemCategory="120" problemType="570425421"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237699828" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="29" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;public void addInterest() {&#xA;&#x9;&#x9;balance += interestRate / 100;&#xA;&#x9;}&#xA;" edit="/1/@proposals.0/@proposals.0/@attempts.7/@edit" start="407" end="-3"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548237809722" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="29" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="balance * " edit="/1/@proposals.0/@proposals.0/@attempts.8/@edit" start="449" end="-26"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548238033437" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="29" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="//" edit="/1/@proposals.0/@proposals.0/@attempts.9/@edit" start="436" end="-49"/>
        </attempts>
        <attempts xsi:type="jdt:JdtSourceEditEvent" timestamp="1548238045152" resourcePath="/ovinger/src/stateandbehavior/Account.java" sizeMeasure="29" className="stateandbehavior.Account">
          <edit xsi:type="exercise:ReplaceSubstringEdit" storedString="&#x9;" edit="/1/@proposals.0/@proposals.0/@attempts.10/@edit" start="436" end="-48"/>
        </attempts>
      </proposals>
      <proposals xsi:type="jdt:JdtLaunchProposal" question="/0/@parts.0/@tasks.1/@q" answer="/0/@parts.0/@tasks.1/@a">
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237351281" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237378390" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237502509" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237643795" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237701708" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237772255" mode="debug" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput>JRE Oracle Corporation/11 is not supported, advanced source lookup disabled.
</consoleOutput>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548237815703" mode="debug" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput>JRE Oracle Corporation/11 is not supported, advanced source lookup disabled.
</consoleOutput>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548238008557" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548238035453" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
        <attempts xsi:type="jdt:JdtLaunchEvent" timestamp="1548238047528" mode="run" className="stateandbehavior.AccountTest">
          <launchAttrNames>org.eclipse.jdt.junit.CONTAINER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.junit.TEST_KIND</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.CLASSPATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.MAIN_TYPE</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.PROJECT_ATTR</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.SOURCE_PATH_PROVIDER</launchAttrNames>
          <launchAttrNames>org.eclipse.jdt.launching.VM_ARGUMENTS</launchAttrNames>
          <launchAttrValues></launchAttrValues>
          <launchAttrValues>org.eclipse.jdt.junit.loader.junit4</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.classpathProvider</launchAttrValues>
          <launchAttrValues>stateandbehavior.AccountTest</launchAttrValues>
          <launchAttrValues>ovinger</launchAttrValues>
          <launchAttrValues>org.eclipse.m2e.launchconfig.sourcepathProvider</launchAttrValues>
          <launchAttrValues>-ea</launchAttrValues>
          <consoleOutput></consoleOutput>
        </attempts>
      </proposals>
      <proposals xsi:type="junit:JunitTestProposal" question="/0/@parts.0/@tasks.2/@q" answer="/0/@parts.0/@tasks.2/@a">
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237355422" completion="NaN" testRunName="AccountTest"/>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237380560" completion="0.0" testRunName="stateandbehavior.AccountTest" errorCount="1">
          <errorTests>initializationError</errorTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237504709" completion="0.25" testRunName="stateandbehavior.AccountTest" successCount="1" errorCount="3">
          <successTests>testConstructor</successTests>
          <errorTests>testAddInterest</errorTests>
          <errorTests>testDeposit</errorTests>
          <errorTests>testDepositNegative</errorTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237646364" completion="0.75" testRunName="stateandbehavior.AccountTest" successCount="3" errorCount="1">
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
          <errorTests>testAddInterest</errorTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237703836" completion="0.75" testRunName="stateandbehavior.AccountTest" successCount="3" failureCount="1">
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
          <failureTests>testAddInterest</failureTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548237827974" completion="1.0" testRunName="stateandbehavior.AccountTest" successCount="4">
          <successTests>testAddInterest</successTests>
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548238011287" completion="1.0" testRunName="stateandbehavior.AccountTest" successCount="4">
          <successTests>testAddInterest</successTests>
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548238038324" completion="0.75" testRunName="stateandbehavior.AccountTest" successCount="3" failureCount="1">
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
          <failureTests>testAddInterest</failureTests>
        </attempts>
        <attempts xsi:type="junit:JunitTestEvent" timestamp="1548238049767" completion="1.0" testRunName="stateandbehavior.AccountTest" successCount="4">
          <successTests>testAddInterest</successTests>
          <successTests>testDeposit</successTests>
          <successTests>testDepositNegative</successTests>
          <successTests>testConstructor</successTests>
        </attempts>
      </proposals>
    </proposals>
    <proposals exercisePart="/0/@parts.1">
      <proposals xsi:type="workbench:DebugEventProposal" question="/0/@parts.1/@tasks.0/@q" answer="/0/@parts.1/@tasks.0/@a">
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237774929" elementId="stateandbehavior.Account" action="suspend.breakpoint"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237818290" elementId="stateandbehavior.Account" action="suspend.breakpoint"/>
      </proposals>
      <proposals xsi:type="workbench:CommandExecutionProposal" question="/0/@parts.1/@tasks.1/@q" answer="/0/@parts.1/@tasks.1/@a">
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237340365" elementId="org.eclipse.ui.file.import" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237340365" elementId="org.eclipse.ui.file.import" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237351258" elementId="org.eclipse.jdt.junit.junitShortcut.run" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237370219" elementId="org.eclipse.ui.project.buildAll" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237411447" elementId="org.eclipse.ui.views.showView" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237411447" elementId="org.eclipse.ui.views.showView" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237478969" elementId="org.eclipse.ui.edit.text.copyLineDown" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237479959" elementId="org.eclipse.ui.edit.text.select.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237481935" elementId="org.eclipse.ui.edit.text.goto.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237483609" elementId="org.eclipse.ui.edit.text.goto.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237485131" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237485889" elementId="org.eclipse.ui.edit.text.goto.lineEnd" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237489414" elementId="org.eclipse.ui.edit.text.contentAssist.proposals" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237490985" elementId="org.eclipse.ui.edit.text.delete.line" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237492848" elementId="org.eclipse.ui.edit.undo" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237500448" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237502508" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237514060" elementId="org.eclipse.ui.edit.text.goto.lineEnd" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237516482" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237601818" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237626597" elementId="org.eclipse.ui.edit.text.contentAssist.proposals" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237629829" elementId="org.eclipse.ui.edit.text.contentAssist.proposals" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237632931" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237641247" elementId="org.eclipse.ui.edit.text.delete.line" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237641653" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237643794" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237687927" elementId="org.eclipse.ui.edit.text.contentAssist.proposals" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237691780" elementId="org.eclipse.ui.edit.text.contentAssist.proposals" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237699835" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237701706" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237765758" elementId="AUTOGEN:::org.eclipse.jdt.debug.CompilationUnitEditor.BreakpointRulerActions/org.eclipse.jdt.debug.ui.actions.ManageBreakpointRulerAction" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237801963" elementId="org.eclipse.debug.ui.commands.Terminate" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237808611" elementId="org.eclipse.ui.edit.text.goto.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237808768" elementId="org.eclipse.ui.edit.text.goto.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237808952" elementId="org.eclipse.ui.edit.text.goto.wordPrevious" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237809731" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237815701" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237827971" elementId="org.eclipse.debug.ui.commands.Resume" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548237830211" elementId="AUTOGEN:::org.eclipse.jdt.debug.CompilationUnitEditor.BreakpointRulerActions/org.eclipse.jdt.debug.ui.actions.ManageBreakpointRulerAction" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238000008" elementId="org.eclipse.ui.edit.delete" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238002555" elementId="org.eclipse.ui.project.buildAll" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238008557" elementId="org.eclipse.jdt.junit.junitShortcut.run" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238033061" elementId="org.eclipse.jdt.ui.edit.text.java.toggle.comment" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238033443" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238035451" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238044758" elementId="org.eclipse.jdt.ui.edit.text.java.toggle.comment" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238045158" elementId="org.eclipse.ui.file.save" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238047527" elementId="org.eclipse.jdt.junit.junitShortcut.rerunLast" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238135493" elementId="org.eclipse.ui.file.restartWorkbench" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548238157685" elementId="org.eclipse.ui.file.properties" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548246990177" elementId="org.eclipse.ui.file.exit" action="executeSuccess"/>
        <attempts xsi:type="workbench:WorkbenchTaskEvent" timestamp="1548249305452" elementId="org.eclipse.equinox.p2.ui.sdk.install" action="executeSuccess"/>
      </proposals>
      <proposals xsi:type="workbench:PartTaskProposal" question="/0/@parts.1/@tasks.2/@q" answer="/0/@parts.1/@tasks.2/@a">
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171579779" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="opened" inputUri="platform:/resource/ovinger/ovinger.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171579788" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="broughtToTop" inputUri="platform:/resource/ovinger/ovinger.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171579792" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171579821" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="activated" inputUri="platform:/resource/ovinger/ovinger.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171595466" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="deactivated" inputUri="platform:/resource/ovinger/ovinger.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171595486" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548171595497" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="closed" inputUri="platform:/resource/ovinger/ovinger.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237355145" elementId="org.eclipse.jdt.junit.ResultView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237355529" elementId="org.eclipse.jdt.junit.ResultView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237356152" elementId="org.eclipse.ui.console.ConsoleView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237356175" elementId="org.eclipse.ui.console.ConsoleView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237371311" elementId="org.eclipse.pde.runtime.LogView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237401430" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="opened" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237401477" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237401481" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237401517" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237411386" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237411416" elementId="no.hal.learning.exercise.views.ExerciseView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237411432" elementId="no.hal.learning.exercise.views.ExerciseView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237411441" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237413860" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237413870" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237417699" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237417716" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237422813" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237422828" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237502353" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237502370" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237510399" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237510414" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237643647" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237643660" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237650419" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237650424" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237655919" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237655933" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237701566" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237701579" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237739027" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237739041" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775071" elementId="org.eclipse.debug.ui.DebugView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775115" elementId="org.eclipse.debug.ui.DebugView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775211" elementId="org.eclipse.ui.console.ConsoleView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775818" elementId="org.eclipse.pde.runtime.LogView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775927" elementId="org.eclipse.debug.ui.VariableView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237775943" elementId="org.eclipse.debug.ui.DebugView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237776003" elementId="org.eclipse.debug.ui.BreakpointView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237801811" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237801827" elementId="org.eclipse.debug.ui.DebugView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237802111" elementId="org.eclipse.debug.ui.DebugView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237802119" elementId="org.eclipse.pde.runtime.LogView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237802135" elementId="org.eclipse.pde.runtime.LogView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237803362" elementId="org.eclipse.pde.runtime.LogView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237803401" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237812477" elementId="org.eclipse.jdt.junit.ResultView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237812478" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237812488" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237818326" elementId="org.eclipse.ui.console.ConsoleView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237818391" elementId="org.eclipse.debug.ui.DebugView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237818635" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237818647" elementId="org.eclipse.debug.ui.VariableView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237818663" elementId="org.eclipse.debug.ui.VariableView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237819210" elementId="org.eclipse.pde.runtime.LogView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237825935" elementId="org.eclipse.debug.ui.DebugView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237825935" elementId="org.eclipse.debug.ui.VariableView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237825960" elementId="org.eclipse.debug.ui.DebugView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237828218" elementId="org.eclipse.debug.ui.DebugView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237828227" elementId="org.eclipse.pde.runtime.LogView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237828237" elementId="org.eclipse.pde.runtime.LogView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237829900" elementId="org.eclipse.pde.runtime.LogView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237829917" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237832573" elementId="org.eclipse.jdt.junit.ResultView" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237832574" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237832583" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237840436" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237840448" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237843416" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237843425" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237846739" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="opened" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237846752" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237846754" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237846764" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237857096" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237857098" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237857117" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237857127" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="closed" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237976805" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237976814" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237991763" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="opened" inputUri="platform:/resource/ovinger/tests/stateandbehavior/AccountTest.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237991768" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="platform:/resource/ovinger/tests/stateandbehavior/AccountTest.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237991770" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237991788" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/tests/stateandbehavior/AccountTest.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237996422" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237996424" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/tests/stateandbehavior/AccountTest.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237996434" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237996451" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="platform:/resource/ovinger/tests/stateandbehavior/AccountTest.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237997355" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548237997363" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238013440" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238013454" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238015383" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238015396" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238032229" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238032242" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238035295" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238035307" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238043907" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238043921" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238047376" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238047387" elementId="org.eclipse.jdt.junit.ResultView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238115660" elementId="org.eclipse.jdt.junit.ResultView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238115679" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135137" elementId="org.eclipse.pde.runtime.RegistryBrowser" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135154" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135168" elementId="org.eclipse.ui.console.ConsoleView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135177" elementId="org.eclipse.debug.ui.DebugView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135188" elementId="org.eclipse.pde.runtime.LogView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135197" elementId="org.eclipse.debug.ui.VariableView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135206" elementId="org.eclipse.debug.ui.BreakpointView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135214" elementId="org.eclipse.jdt.junit.ResultView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135222" elementId="no.hal.learning.exercise.views.ExerciseView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135246" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238135247" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238146275" elementId="no.hal.learning.exercise.views.ExerciseView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238146454" elementId="org.eclipse.pde.runtime.LogView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238146664" elementId="org.eclipse.jdt.junit.ResultView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238146778" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238151060" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238151079" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238158884" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238158904" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238161887" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238161900" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286541" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286553" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286897" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="opened" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286914" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286916" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238286945" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238313625" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238313738" elementId="org.eclipse.ui.views.PropertySheet" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238313753" elementId="org.eclipse.ui.views.PropertySheet" action="broughtToTop"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238313767" elementId="org.eclipse.ui.views.PropertySheet" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238320118" elementId="org.eclipse.ui.views.PropertySheet" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238320130" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238323285" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548238323296" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246887709" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246887746" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246912293" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="opened" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246912328" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246912331" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246912369" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246915681" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246915689" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246926574" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246926593" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246936743" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="opened" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246936769" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246936771" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246936797" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246936930" elementId="no.hal.learning.exercise.presentation.ExerciseEditorID" action="closed" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.ex"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246971932" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="broughtToTop" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246971933" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246971947" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246971959" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246972806" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246972815" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246973112" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246973130" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="activated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246978553" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="deactivated" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246978569" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989817" elementId="org.eclipse.pde.runtime.RegistryBrowser" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989831" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989852" elementId="org.eclipse.jdt.junit.ResultView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989859" elementId="org.eclipse.pde.runtime.LogView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989868" elementId="org.eclipse.ui.views.PropertySheet" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989877" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989896" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548246989896" elementId="no.hal.learning.exercise.views.ExerciseView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249237231" elementId="no.hal.learning.exercise.views.ExerciseView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249237329" elementId="org.eclipse.ui.views.PropertySheet" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249237505" elementId="org.eclipse.jdt.junit.ResultView" action="opened"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249237584" elementId="no.hal.learning.exercise.views.ExerciseView" action="activated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311913" elementId="org.eclipse.pde.runtime.RegistryBrowser" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311929" elementId="org.eclipse.ui.navigator.ProjectExplorer" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311948" elementId="org.eclipse.jdt.junit.ResultView" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311958" elementId="org.eclipse.ui.views.PropertySheet" action="closed"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311972" elementId="org.eclipse.jdt.ui.CompilationUnitEditor" action="closed" inputUri="platform:/resource/ovinger/src/stateandbehavior/Account.java"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311994" elementId="no.hal.learning.exercise.views.ExerciseView" action="deactivated"/>
        <attempts xsi:type="workbench:PartTaskEvent" timestamp="1548249311995" elementId="no.hal.learning.exercise.views.ExerciseView" action="closed"/>
      </proposals>
    </proposals>
  </exercise:ExerciseProposals>
</xmi:XMI>
